//
// Created by abc84 on 2016/5/5.
//

#ifndef TRAIN_STACK_STACK_H
#define TRAIN_STACK_STACK_H


#include <vector>
#include <iostream>

template <typename T>
class Stack : public std::vector<T>{
public:
    void push(T val);
    T pop();
    int size(){ return std::vector<T>::size();};
};

template <typename T>
void Stack<T>::push(T val) {
    this->push_back(val);
}
template <typename T>

T Stack<T>::pop() {
    //std::cout<<"size()"<<size()<<std::endl;
    T e = (*this)[size() - 1];
    this->pop_back();
    return e;
}


#endif //TRAIN_STACK_STACK_H
