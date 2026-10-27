#!/bin/bash
# Student: Miras Yeren | Group IT2-2308 | ID 41517
# Writes a system report to logs/Yeren_Miras_system_report.log

STUDENT="Miras Yeren"
LOG_FILE="$(dirname "$0")/../logs/Yeren_Miras_system_report.log"

{
  echo "=== System report ==="
  echo "Student:      $STUDENT"
  echo "Date/time:    $(date '+%Y-%m-%d %H:%M:%S')"
  echo "Username:     $(whoami)"
  echo "OS:           $(uname -s)"
  echo "Kernel:       $(uname -r)"
  echo "CPU:          $(grep -m1 'model name' /proc/cpuinfo | cut -d: -f2 | xargs)"
  echo "Memory:"
  free -h
  echo "Disk usage:"
  df -h /
  echo "Available disk space: $(df -h / | awk 'NR==2 {print $4}')"
  echo
} | tee -a "$LOG_FILE"
