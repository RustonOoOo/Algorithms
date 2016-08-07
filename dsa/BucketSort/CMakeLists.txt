cmake_minimum_required(VERSION 3.5)
project(BucketSort)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES main.cpp Buckets.cpp Buckets.h)
add_executable(BucketSort ${SOURCE_FILES})