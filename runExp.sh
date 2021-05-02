for SEQM in LSTM Transformer #GRU
do
    DLG=Yes
    POV=None
    for EXP in 0 1 2 3 4 5 6 7 8
    do
        echo python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --save_path=out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
        python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --save_path=out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
    done

    for DLG in Yes No
    do
        POV=Third
        for EXP in 0 1 2
        do
            echo python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --save_path=out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
            python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --save_path=out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
        done

        POV=First
        for EXP in 3 4 5 #6 7 8
        do
            echo python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --save_path=out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch
            python3 experiments/sandbox.py --use_dialogue=$DLG --pov=$POV --seq_model=$SEQM --experiment=$EXP --save_path=out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.torch > out/dialogue_${DLG}_pov_${POV}_${SEQM}_${EXP}.log 2>&1
        done
    done
done

