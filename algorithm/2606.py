computer_list = []
N = int(input())
con_cnt = int(input())

for i in range(0, N):
    line = list(map(int, input().split()))
    try:
        computer_list.insert(line[0], computer_list[line[0]].append(line[1]))
    except IndexError:
        computer_list.insert(line[0], [line[1]])

    try:
        computer_list.insert(line[1], computer_list[line[1]].append(line[0]))
    except IndexError:
        computer_list.insert(line[1], [line[0]])

print(computer_list)