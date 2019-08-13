cnt = 6;
total_list = []

while 1:
    input_line = input()
    if input_line is "0":
        break
    line = list(map(int, input_line.split()))
    count = line[0]
    del line[line.index(line[0])]
    total_list.append([count, line])


def print_func(N, list, index, depth, pre_str):
    sum_str = pre_str + str(list[index]) + " "

    if depth is cnt - 1:
        print(sum_str, end='\n')
    else:
        for i in range(index + 1, min(N - 4 + depth, N)):
            print_func(N, list, i, depth + 1, sum_str)


def lotto(N, list):
    for start_index in range(0, N - cnt + 1):
        print_func(N, list, start_index, 0, "")
    print("\n", end='')


for i in range(0, len(total_list)):
    lotto(total_list[i][0], total_list[i][1])