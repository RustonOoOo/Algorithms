//
// Created by abc84 on 2016/5/2.
//

#ifndef ZUMA_LIST_H
#define ZUMA_LIST_H

#include <search.h>
#include "ListNode.h"
typedef int Rank;
template <typename T>
class List {
private:
    size_t size_;
    ListNode<T>*header;
    ListNode<T>*footer;
    void init();//init header,footer
    void clear();
public:
    ListNode<T>* begin(){ return header->next;}
    ListNode<T>* end(){ return footer;}
    size_t size(){ return size_;}
    List(){init();}
    List(std::string s);
    ~List();
    T remove(ListNode<T>* pos);
    T remove(Rank n);
    ListNode<T>* remove(ListNode<T>* start, ListNode<T>* end);//range remove return
                                                        // the star's pre pos
    ListNode<T>* insertBefore(Rank n, T val);
    ListNode<T>* insertBefore(ListNode<T>* pos, T val);
    void insert(T val);//添加到末尾(Footer之前)
};
template <typename T>
void List<T>::init() {
    header = new ListNode<T>;
    footer = new ListNode<T>;
    header->next = footer;
    footer->prev = header;
    header->prev = NULL;
    header->val = 0;
    footer->next = NULL;
    size_ = 0;
}
template <typename T>
ListNode<T>* List<T>::insertBefore(ListNode<T>* pos, T val) {
    size_++;
    return pos->insertAsPrev(val);
}

template <typename T>
ListNode<T>* List<T>::insertBefore(Rank n, T val) {
    ListNode<T>* cur = header->next;
    for (int i = n; i > 0; i--) {
        cur = cur->next;
    }
    return insertBefore(cur, val);
}

template <typename T>
void List<T>::insert(T val) {
    insertBefore(footer, val);
}

template <typename T>
T List<T>::remove(Rank n) {
    ListNode<T>* cur = header->next;
    for (int i = n; i > 0; i--) {
        cur = cur->next;
    }
    T e = cur->val;
    remove(cur);
    return e;
}

template <typename T>
T List<T>::remove(ListNode<T> *pos) {
    T e = pos->val;
    pos->prev->next = pos->next;
    pos->next->prev = pos->prev;
    delete pos;
    size_--;
    return e;
}

template <typename T>
ListNode<T>* List<T>::remove(ListNode<T> *start, ListNode<T> *end) {
    ListNode<T> *next = start->next;
    ListNode<T> *cur = start;
    while(cur != end) {
        remove(cur);
        cur = next;//record next
        next = next->next;
    }
    return start->prev;
}

template <typename T>
void List<T>::clear() {
    while (size_ > 0) {
        remove(header->next);
    }
    //std::cout<<"all cleared"<<std::endl;
}

template <typename T>
List<T>::~List() {
    clear();
    delete header;
    delete footer;
}
template <typename T>
List<T>::List(std::string s) {
    init();
    for (int i = 0; i < s.size(); ++i) {
        this->insert(s[i]);
    }
}



#endif //ZUMA_LIST_H
