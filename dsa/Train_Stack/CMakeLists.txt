cmake_minimum_required(VERSION 3.5)
project(Train_Stack)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Stack.cpp Stack.h)
add_executable(Train_Stack ${SOURCE_FILES})