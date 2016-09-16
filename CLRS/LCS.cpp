#include <iostream>
#include <string>
#include <vector>
using  namespace std;

int LCS(string a, string b,vector<vector<int>>&d) {//DP
    size_t lenA = a.length(),lenB = b.length();
    if(lenA == 0 || lenB == 0)return 0;
    if(d[lenA][lenB] > 0)return d[lenA][lenB];
    if(a.back() == b.back())
        d[lenA][lenB] = LCS(a.substr(0,lenA-1),b.substr(0,lenB-1),d) + 1;
    else {
        d[lenA][lenB] = max(LCS(a.substr(0,lenA-1),b,d),
                                LCS(a,b.substr(0,lenB-1),d));
    }
    return d[lenA][lenB];
}


int LCS(string a, string b) {//RECURSIVE
    size_t lenA = a.length(),lenB = b.length();
    if(lenA == 0 || lenB == 0)return 0;
    if(a.back() == b.back())
        return LCS(a.substr(0,lenA-1),b.substr(0,lenB-1)) + 1;
    else {
        return max(LCS(a.substr(0,lenA-1),b),
                            LCS(a,b.substr(0,lenB-1)));
    }
}
int main() {
//    string a = "abmcmdemfgmmhmijmkxx";
//    string b = "abxcdxexxxxfxxgxxhxxxxixxxxxjxkxx";
    string a = "axbxcxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    string b = "atttttttttttttttttttttttttttttttttttttttttttttttttttttttttbtc";
    vector<vector<int>> d(a.length()+1,vector<int>(b.length()+1,0));
    d[0][0] = 0;
    d[0][1] = 0;
    d[1][0] = 0;
    cout<<LCS(a,b,d);
}