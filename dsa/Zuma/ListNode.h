//
// Created by abc84 on 2016/5/2.
//

#ifndef ZUMA_LISTNODE_H
#define ZUMA_LISTNODE_H

#include <iostream>
//typedef next ListNode*;
template <typename T>
struct ListNode {
    ListNode(){}
    ListNode(T v, ListNode<T>* pre, ListNode<T>* nex):
            val(v), next(nex), prev(pre){}
    T val;
    ListNode<T>* next;
    ListNode<T>* prev;
    void insertAsNext(T val);
    ListNode<T>* insertAsPrev(T val);
};

template <typename T>
void ListNode<T>::insertAsNext(T val) {
    ListNode<T>* node = new ListNode(val, this, this->next);
    this->next->prev = node;
    this->next = node;
}
template <typename T>
ListNode<T>* ListNode<T>::insertAsPrev(T val) {
    ListNode<T>* node = new ListNode(val, this->prev, this);
    this->prev->next = node;
    this->prev = node;
    return node;
}



#endif //ZUMA_LISTNODE_H
