whiteList = []
blackList = []
whiteCount = 0
blackCount = 0
whiteTotalPower = 0
blackTotalPower = 0


# 87 84
# 66 78
# 86 94
# 93 87
# 72 100

while True:
    inputValue = input()
    if inputValue is "":
        break
    item = list(map(int, inputValue.split()))
    whiteList.append(item)
    blackList.append(item)

whiteList.sort(key=lambda white: white[0], reverse=True)
blackList.sort(key=lambda black: black[1], reverse=True)

for whiteIndex in range(0, 30):
    blackIndex = blackList.index(whiteList[whiteIndex])
    if whiteIndex > blackIndex:
        whiteTotalPower += whiteList[whiteIndex][0]
        whiteCount += 1
    if whiteIndex < blackIndex:
        blackTotalPower += blackList[blackIndex][1]
        blackCount += 1
    else:
        if whiteCount < blackCount:
            whiteTotalPower += whiteList[whiteIndex][0]
        else:
            blackTotalPower += blackList[whiteIndex][1]

print(whiteTotalPower + blackTotalPower)