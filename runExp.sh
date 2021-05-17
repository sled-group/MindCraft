# for SEQM in LSTM
# do
#     for EXP in  0
#     do
#         DLG=Yes
#         POV=None
        
#         echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#         python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#         # echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#         # python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1

#         POV=Third
#         for DLG in Yes No
#         do
#             echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#             python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#             # echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#             # python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#         done
#     done
# done

# for SEQM in Transformer LSTM
# do
#     for EXP in  1 2
#     do
#         DLG=Yes
#         POV=None
        
#         echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#         python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#         # echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#         # python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1

#         POV=Third
#         for DLG in Yes No
#         do
#             echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#             python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#             # echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#             # python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#         done
#     done
# done

# for rseed in a #b c d e #f g h i #j
# do
#     for SEQM in Transformer LSTM
#     do
#         for EXP in 0 1
#         do
#             DLG=Yes
#             POV=None
#             echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#             python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#             # echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#             # python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1

#             POV=Third
#             for DLG in Yes No
#             do
#                 echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#                 python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#                 # echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#                 # python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#             done
#         done
#     done
# done

# for rseed in a #b c d e #f g h i #j
# do
#     for SEQM in Transformer LSTM
#     do
#         for EXP in 2
#         do
#             DLG=Yes
#             POV=None
#             echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#             python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#             echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#             python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1

#             POV=Third
#             for DLG in Yes No
#             do
#                 echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#                 python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#                 echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#                 python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#             done
#         done
#     done
# done

for rseed in f g h i j #b c d e #
do
    for SEQM in Transformer LSTM
    do
        for EXP in 0 1 2
        do
            DLG=Yes
            POV=None
            echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
            python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
            echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
            python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1

            POV=Third
            for DLG in Yes No
            do
                echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
                python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
                echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
                python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=No --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
            done
        done
    done
done


# for rseed in a b c d e #f g h #i j #a
# do
#     for SEQM in Transformer LSTM #GRU LSTM #
#     do
#         DLG=Yes
#         POV=None
#         for EXP in  6 7 8 #0 1 2 3 4 5 6 7 8
#         do
#             echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#             python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#         done

#         # for DLG in Yes No #No #
#         # do
#         #     # POV=Third
#         #     # for EXP in 0 1 2
#         #     # do
#         #     #     echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#         #     #     python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#         #     # done

#         #     # POV=First
#         #     # for EXP in 6 7 8 #3 4 5 #6 7 8
#         #     # do
#         #     #     echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch
#         #     #     python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Random --plans=Yes --save_path=out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.torch > out_with_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}_${rseed}.log 2>&1
#         #     # done
#         # done
#     done
# done

# for SEQM in LSTM #Transformer #GRU #
# do
#     DLG=Yes
#     POV=None
#     for EXP in  6 7 8 #0 1 2 3 4 5 6 7 8
#     do
#         echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#         python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#     done

#     for DLG in Yes No #No #
#     do
#         # POV=Third
#         # for EXP in 0 1 2
#         # do
#         #     echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#         #     python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#         # done

#         POV=First
#         for EXP in 6 7 8 #3 4 5 #6 7 8
#         do
#             echo $(date) python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
#             python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --seed=Fixed --save_path=out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out_without_plan/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
#         done
#     done
# done

echo Done