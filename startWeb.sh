current_time=$(cat log_dest.tmp)

log_file="logs/${current_time}/web_${current_time}.log"

cd mean

npm start | tee -a ../${log_file}