cmake_minimum_required(VERSION 3.6)
project(BuddhaServer)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp "9-5 unblockconnect.cpp" "9-6 mytalk_client.cpp" 9-7mytalk_server.cpp "9-8 multi_port.cpp" 10-1unievent.cpp "13-3 IPC_PRIVATE.cpp" 13-4shm_talk_server.cpp processpool.h)
add_executable(BuddhaServer ${SOURCE_FILES})