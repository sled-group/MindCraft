#! /bin/bash
# Bash script to start server.

# Generate Plan
printf "\nGenerating plan..."
python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=2 --lower_complex_lim=2 --num_tools=2 --disparate_knowledge
# python3 plan_generator/plan_generator.py --num_final_mat=1 --output_path=spigot/plan.json --upper_complex_lim=1 --lower_complex_lim=1 --num_tools=2
printf "Done!\n"

# Start Server
printf "\nStarting Server...\n\n"
bash spigot/start.command
