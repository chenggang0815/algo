import sys

class heap:
    def __init__(self, arr, size):
        self.arr = arr
        self.size = size

    def insert(self, val):
        self.arr[self.size] = val
        self.size += 1
        self.heapifyUp(self.arr, self.size, self.size - 1)

    def extractMin(self):
        res = self.arr[0]

        self.arr[0] = self.arr[self.size - 1]
        self.size -= 1
        #self.arr.pop()
        self.heapifyDown(self.arr, self.size, 0)

        return res


    def heapifyDown(self, arr, size, parent):
        child_left = 2 * parent
        child_right = 2 * parent + 1
        while 2 * parent < self.size:
            if self.arr[parent] > min(self.arr[child_right], self.arr[child_left]):
                if self.arr[child_left] < self.arr[child_right]:
                    temp = self.arr[child_left]
                    self.arr[child_left] = self.arr[parent]
                    self.arr[parent] = temp
                    parent = child_left
                else:
                    temp = self.arr[child_right]
                    self.arr[child_right] = self.arr[parent]
                    self.arr[parent] = temp
                    parent = child_right
                child_left = 2 * parent
                child_right = 2 * parent + 1
            else:
                break

    def heapifyUp(self, arr, size, child):
        parent = child // 2
        while parent >= 0:
            if self.arr[parent] > self.arr[child]:
                temp = self.arr[parent]
                self.arr[parent] = self.arr[child]
                self.arr[child] = temp
                child = parent
                parent = child // 2
            else:
                break


if __name__ == '__main__':

    # result = []
    # heap = heap([0]*200000, 0)
    # while True:
    #     line = sys.stdin.readline().strip()
    #     if not line:
    #         break
    #     if line.split()[0] == 'A':
    #         heap.insert(int(line.split()[1]))
    #     else:
    #         result.append(heap.extractMin())
    #
    # for i in range(len(result)):
    #     print(result[i])

    heap = heap([0]*200000, 0)
    heap.insert(300)
    print(heap.extractMin())
    heap.insert(200)
    heap.insert(600)
    heap.insert(500)
    print(heap.extractMin())
    heap.insert(100)
    #print(heap.extractMin())
    # heap.insert(100)
    print(heap.extractMin())
    print(heap.extractMin())
    print(heap.extractMin())
