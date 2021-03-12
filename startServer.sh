#! /bin/bash
# Bash script to start server.

# Spigot Logs
mkdir -p logs
current_time=$(date "+%Y.%m.%d-%H.%M.%S")
log_file="logs/logs.${current_time}.txt"
plan_file="logs/logs.${current_time}.plan.json"
touch $log_file
printf "\nLog file created ${log_file}" | tee -a $log_file

# Generate Plan

printf "\nGenerating plan..."
# python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=2 --disparate_knowledge
# python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=2
python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=1 --disparate_knowledge
# python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=1
cp spigot/plan.json $plan_file
cp spigot/plan.json mean/plan.json
printf "Done!\n"


# Start Server
printf "\nStarting Server...\n\n" | tee -a $log_file
bash spigot/start.command 2>&1 | tee -a $log_file

mysqldump --add-drop-table -u root --password minecraft > "logs/mcc_${current_time}.sql"
