#include <iostream>
#include "Stack.h"
/*
 * 列车调度问题
 */
using namespace std;
void  matchOperations(vector<string> &ops, int trainsOutSeq[], int n,int maxStackDeep);
void pushInStackOp(vector<string> &ops, Stack<int> &seq, int start, int dif);
bool checkDif(vector<string> &ops, Stack<int> &seq, int dif, int maxStackDeep);//是否diff大于栈深，或是否符合序列（如312这样的就不符合，因为1-3=-2）
int main() {
    int n,m;//车队长度以及最大栈深
    cin>>n>>m;
    //初始化车队序列
    int trainsIn[n];
    for (int i = 0; i < n; i++) {
        trainsIn[i] = i+1;//1-n的序列
    }
    //出栈后的序列
    int trainsOut[n];
    for (int i = 0; i < n; i++) {
        cin>>trainsOut[i];
    }
    //操作方式的序列
    vector<string> ops;
    matchOperations(ops, trainsOut, n, m);
    if (ops.empty()) {//若为空则表示没有任何操作能达到该序列
        cout<<"No"<<endl;
    }
    else {
        for (string s : ops) {
            cout<<s<<endl;
        }
    }
    return 0;
}
//匹配正确的栈操作序列
void  matchOperations(vector<string> &ops,int trainsOutSeq[],int n, int maxStackDeep) {
    //vector<string> ops;
    int finalSeq[n];
    Stack<int> s;
    int remainInOutSeq = n;
    for (int i = 0; i < n; ++i) {
        if (s.empty()) {//s为空时，计算seq第一个元素与该下标差值，该差值即为push次数
            int dif = trainsOutSeq[i] - i;
            if (!checkDif(ops, s, dif, maxStackDeep))return;
//            if (dif > maxStackDeep) {//若差值大于最大栈深则匹配失败
//                s.clear();
//                cout<<"greater than maxStackDeep"<<endl;
//                return;
//            }
            pushInStackOp(ops, s, i, dif);
            remainInOutSeq -= dif;
//            for (int j = i; j < dif + i; ++j) {
//                s.push(j+1);
//                remainInOutSeq--;
//                ops.push_back("push");
//            }
//            ops.push_back("pop");//push完后立即pop进入实际上的seq
            finalSeq[i] = s.pop();
        }
        else {//就以前面那个 为基准计算差值，差多少就push多少,若差值为负即为pop
            int dif = trainsOutSeq[i] - trainsOutSeq[i-1];
            if (remainInOutSeq == 0) {//此时车队队列空了（不是s栈空）
                ops.push_back("pop");
                finalSeq[i] = s.pop();
                continue;
            }
            if (!checkDif(ops, s, dif, maxStackDeep))return;
//            if (dif <= -2 || dif > maxStackDeep) {//若差值不大于-2，则为不可能操作出来的序列
//                s.clear();
//                cout<<"greater than maxStackDeep"<<endl;
//                ops.clear();
//                return;
//                //return ops;
//            }
            if (dif == -1) {
                ops.push_back("pop");
                finalSeq[i] = s.pop();
                continue;
            }
            pushInStackOp(ops, s, trainsOutSeq[i], dif);
            remainInOutSeq -= dif;
//            for (int j = trainsOutSeq[i-1]; j < dif + trainsOutSeq[i-1]; ++j) {
//                s.push(j+1);
//                remainInOutSeq--;
//                ops.push_back("push");
//            }
//            ops.push_back("pop");
            finalSeq[i] = s.pop();
        }
    }
    //return ops;
}
void pushInStackOp(vector<string> &ops, Stack<int> &seq, int start, int dif) {
    for (int i = start; i < start + dif; ++i) {//从start 处push到start + dif处
        ops.push_back("push");
        seq.push(i + 1);
    }
    ops.push_back("pop");//把最后push进来的pop
}

bool checkDif(vector<string> &ops, Stack<int> &seq, int dif, int maxStackDeep) {//是否diff大于栈深，或是否符合序列（如312这样的就不符合，因为1-3=-2）
    if (dif <= -2 || dif > maxStackDeep) {//若差值不大于-2，则为不可能操作出来的序列
        seq.clear();
        cout<<"greater than maxStackDeep"<<endl;
        ops.clear();
        return false;
        //return ops;
    }
    return true;
}