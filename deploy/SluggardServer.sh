#!/bin/bash
source /etc/profile

project_name="SluggardServer"
project_file="sluggard-server"
project_version="1.0.1"

project_path=$(cd `dirname $0`; pwd)
project_jar="${project_file}-${project_version}.jar"
project_log="${project_file}.log"

usage() {
 echo "Usage: sh $0 [start|stop|restart|status]"
 exit 1
}

#检查程序是否在运行
is_exist(){
 pid=`ps -ef|grep $project_jar|grep -v grep|awk '{print $2}' `
 #如果不存在返回1，存在返回0 
 if [ -z "${pid}" ]; then
 return 1
 else
 return 0
 fi
}
  
#启动
start(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "${project_name} is already running. Pid is ${pid}."
 else
 echo "${project_name} Start."
 nohup java -jar $project_path/$project_jar > $project_path/$project_log 2>&1 &
 fi
}

#停止
stop(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "${project_name} Stop."
 kill -15 $pid
 else
 echo "${project_name} is not running."
 fi
}
  
#输出运行状态
status(){
 is_exist
 if [ $? -eq "0" ]; then
 echo "${project_name} is running. Pid is ${pid}."
 else
 echo "${project_name} is not running."
 fi
}
  
#重启
restart(){
 stop
 sleep 3
 start
}
  
#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
 "start")
 start
 ;;
 "stop")
 stop
 ;;
 "status")
 status
 ;;
 "restart")
 restart
 ;;
 *)
 usage
 ;;
esac
