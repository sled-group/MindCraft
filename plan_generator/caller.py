#! /usr/bin/env python3
import subprocess, json

process = subprocess.Popen(["./plan_generator.py"],stdout=subprocess.PIPE)
(o, e) = process.communicate()
print(json.loads(o))