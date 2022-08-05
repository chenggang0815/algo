numbers = [1,2,3,4]
n = len(numbers)
k = 1
preOddNumList = [0]
for i in range(n):
    isOddNum = numbers[i] % 2
    preOddNumList.append(isOddNum + preOddNumList[-1])
print(preOddNumList)
ans = set()
for i in range(n):
    numStr = ""
    for j in range(i+1, n+1):
        numStr += str(numbers[j-1]) + "$"
        if preOddNumList[j] - preOddNumList[i] <= k:
            ans.add(numStr)
print(len(ans))
print(ans)