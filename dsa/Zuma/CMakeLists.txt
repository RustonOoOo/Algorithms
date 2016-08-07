cmake_minimum_required(VERSION 3.5)
project(Zuma)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp ListNode.cpp ListNode.h List.cpp List.h)
add_executable(Zuma ${SOURCE_FILES})