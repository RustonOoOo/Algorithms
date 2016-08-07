cmake_minimum_required(VERSION 3.3)
project(CountingSort)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp SalesData.cpp SalesData.h)
add_executable(CountingSort ${SOURCE_FILES})