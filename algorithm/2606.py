tot_cnt = 0
computer_list = {}
stack = []
visitList = {}
N = int(input())
con_cnt = int(input())

for i in range(0, con_cnt):
    line = list(map(int, input().split()))
    print(line)
    if line[0] in computer_list:
        computer_list[line[0]].append(line[1])
    else:
        computer_list[line[0]] = [line[1]]

    if line[1] in computer_list:
        computer_list[line[1]].append(line[0])
    else:
        computer_list[line[1]] = [line[0]]

def put(value):
    stack.append(value)

def pop():
    return stack.pop()

def get():
    return stack[len(stack) - 1]

def search(num, cnt):
    # 방문확인
    if num in visitList:
        return 0

    print(num)
    # 방문처리 & stack 입력
    visitList[num] = True
    put(num)

    # 연결개수확인
    cnt + 1

    # 인접한 노드 재귀 돌리기
    for i in computer_list[num]:
        search(i, cnt)

    pop()
    search(get(), cnt)

search(1, tot_cnt)

print(tot_cnt)