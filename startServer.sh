#! /bin/bash
# Bash script to start server.

# Spigot Logs
mkdir -p logs
my_ip=$(ifconfig  | grep 'inet '| grep -v '127.0.0.1' | cut -d: -f2 | awk '{ print $2}' | tr '.' '_')
current_time="${my_ip}_$(date "+%Y%m%d_%H%M%S")"

mkdir -p "logs/${current_time}"

echo $current_time > log_dest.tmp

tmp=$(cat log_dest.tmp)

echo "${tmp}.log"

log_file="logs/${current_time}/mcc_${current_time}.log"
plan_file="logs/${current_time}/plan_${current_time}.json"
touch $log_file
printf "\nLog file created ${log_file}" | tee -a $log_file

# printf "\n"
# exit 1

# Generate Plan

printf "\nGenerating plan..."
python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=1                       # SK-SS
# python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=2 --disparate_knowledge # DK-DS
# python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=1 --disparate_knowledge # DK-SS
# python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=2                       # SK-DS

cp spigot/plan.json $plan_file
cp spigot/plan.json mean/plan.json
printf "Done!\n"


# Start Server
printf "\nStarting Server...\n\n" | tee -a $log_file
bash spigot/start.command 2>&1 | tee -a $log_file

echo "Minecraft server stopped!"

# asks for password
mysqldump --add-drop-table -u root --password minecraft > "logs/${current_time}/mcc_${current_time}.sql"

echo "Dataset Dumped!"

# asks for password
echo "DROP DATABASE minecraft; CREATE DATABASE minecraft;" | mysql -u root -p

echo "Dataset reset!"
