# 10 9
# 10 8
# 10 7
# 10 6
# 10 5
# 10 4
# 10 3
# 10 2
# 10 1
# 10 10
# 10 10
# 9 10
# 10 9
# 10 8
# 10 7
# 9 10
# 8 10
# 7 10
# 10 10
# 10 10
# 10 10
# 10 10
# 10 10
# 10 10
# 10 10
# 10 10
# 10 10
# 8 9
# 8 9
# 8 9

teamList = []
whiteTotal = 0
blackTotal = 0

while True:
    line = input()
    if line is "":
        break
    item = list(map(int, line.split()))
    item.append(0)
    teamList.append(item)


def cal(index, w_cnt, b_cnt):

    maxValue = 0
    print("index:", index, teamList[index], "/w_cnt:", w_cnt, " b_cnt:", b_cnt)
    if index is len(teamList):
        return 0

    if w_cnt < 15:
        print("white:", cal(index + 1, w_cnt + 1, b_cnt), "/", )
        maxValue = max(maxValue, teamList[index][0] + cal(index + 1, w_cnt + 1, b_cnt))

    if b_cnt < 15:
        print("black:", cal(index + 1, w_cnt, b_cnt + 1))
        maxValue = max(maxValue, teamList[index][1] + cal(index + 1, w_cnt, b_cnt + 1))

    return max(maxValue, cal(index + 1, w_cnt, b_cnt))


print(cal(0, 0, 0))