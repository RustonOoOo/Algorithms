#include <iostream>
#include "List.h"
using namespace std;
template <typename N, typename M>
struct Pair{
    N first;
    M second;
    Pair(N f, M s):first(f), second(s){}
};
Pair<size_t,ListNode<char>*> check_front(ListNode<char> *pos);//count the number of the
                                        // same value of pos in the front and the pos
                                        //of first element equal to the val
Pair<size_t,ListNode<char>*> check_back(ListNode<char> *pos);
void test();
int main() {
    string input;
    cin>>input;
    List<char> zuma;
    for (char c : input) {
        zuma.insert(c);
    }
    int times;
    cin>>times;
    while(times > 0) {
        int pos;char c;
        cin>>pos>>c;
        ListNode<char >* cur = zuma.insertBefore(pos, c);
        auto pre_pair = check_front(cur);
        auto back_pair = check_back(cur);
        size_t count = pre_pair.first + back_pair.first + 1;
        while (count >= 3) {
            //cout<<"["<<pre_pair.first<<back_pair.first<<"]"<<endl;
            cur = zuma.remove(pre_pair.second, back_pair.second);
            cout<<pre_pair.second->val<<endl;
            if (zuma.size() <= 0||cur->val == 0){//防止check header footer指针
                //std::cout<<"finish"<<endl;
                break;
            }
            pre_pair = check_front(cur);//check agin pre same value and pos
            back_pair = check_back(cur);//check agin
            count = pre_pair.first + back_pair.first + 1;//same value nums
//            for (ListNode<char> *beg = zuma.begin(); beg != zuma.end(); beg = beg->next) {
//                std::cout<<beg->val;
//            }
//            cout<<endl;
        }
        if (zuma.size() <= 0)std::cout<<"-"<<endl;
        else{
            for (ListNode<char> *beg = zuma.begin(); beg != zuma.end(); beg = beg->next) {
                std::cout<<beg->val;
            }
            cout<<endl;
        }
        times--;
    }
    cout<<endl;
    test();
    return 0;
}
Pair<size_t,ListNode<char>*> check_front(ListNode<char> *pos) {
    size_t count = 0;
    ListNode<char>* cur = pos->prev;
    while(cur->val==pos->val) {
        cur = cur->prev;
        count++;
    }
    return Pair<size_t,ListNode<char>*>(count,cur->next);
}
Pair<size_t,ListNode<char>*> check_back(ListNode<char> *pos) {
    size_t count = 0;
    ListNode<char>* cur = pos->next;
    while(cur->val==pos->val) {
        cur = cur->next;
        count++;
    }
    return Pair<size_t,ListNode<char>*>(count,cur);
}
void test() {
    List<char> testcase("AAABA");
    //                     ~
//    ListNode<char >* cur = testcase.insertBefore(1, 'A');
//    auto p1 = check_front(cur);
//    auto p2 = check_back(cur);
//    int a = p1.first;
//    int b = p2.first;
//    cout<<"a:"<<a<<" "<<"b:"<<b<<endl;
//    cout<<p1.second->val<<endl;
//    cout<<p2.second->val<<endl;
//    if(a==b&&a==2) {
//        cout<<"check front and back size passed"<<endl;
//    }
//    else{
//        cout<<"check front and back size not passed"<<endl;
//    }
//
//    testcase.insertBefore(3,'F');
//    testcase.insertBefore(0,'G');
//    cout<<"after insert"<<endl;
//    for (ListNode<char> *beg = testcase.begin(); beg != testcase.end(); beg = beg->next) {
//        std::cout<<beg->val;
//    }
//    cout<<endl;
    ListNode<char >* cur = testcase.begin()->next;
    auto p1 = check_front(cur);
    auto p2 = check_back(cur);
    testcase.remove(p1.second,p2.second);
    //testcase.remove(p1.second, p2.second);
    cout<<"after remove"<<endl;
    if (testcase.size() <= 0)std::cout<<"-"<<endl;
        else{
            for (ListNode<char> *beg = testcase.begin(); beg != testcase.end(); beg = beg->next) {
                std::cout<<beg->val;
            }
            cout<<endl;
        }
}