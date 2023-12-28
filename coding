#include<iostream>
#include<cmath>
using namespace std;
int main() {
    int n;
    cin>>n;
    int arr[n];
    int arr1[n];
    for(int i=0;i<n;i++)
    {
        cin>>arr[i];
        arr1[i]=arr[i];
    }
    int x=0;
    int y=0; 
    for(int i=4;i>=0;i--){
        if((arr[i]%2!=arr[i-1]%2) || arr[i]==0 || arr[i]==0) {
            continue; }
        else{
            arr[i-1]=arr[i-1]/2;
            i++;
            y++; }}
   for(int i=1;i<5;i++){
        if((arr1[i]%2!=arr1[i-1]%2) || arr1[i]==0 || arr1[i]==0) {
            continue; }
        else{
            arr1[i]=arr1[i]/2;
            i--;
            x++; }}  
   if(x>y){
       cout<<y;
   }
   else
 cout<<x;
    return 0;
}
