# mp-sorting-maven

An exploration of sorting in Java.

Authors

- Khanh Do
- Samuel A. Rebelsky (starter code)

Acknowledgements

- _Forthcoming_.

This code may be found at <https://github.com/khanhdo05/mp-sorting-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

## Description of custom sorting algorithm

`DoKhanhSorter` is a custom sorting algorithm that adaptively blends insertion sort and merge sort. Merge sort is a divide-and-conquer algorithm that works by recursively splitting the array into smaller subarrays, sorting each subarray, and then merging them back together into a single sorted array. Insertion sort is a simple sorting algorithm that is efficient for small datasets. This implementation uses merge sort for larger subarrays and switches to insertion sort for smaller subarrays to optimize performance. This sorter also checks if it is nearly sorted then it would use insertion sort.

Inspired by TimSort.

### Time Complexity

- **Best Case:** O(N)
- **Average Case:** O(N log N)
- **Worst Case:** O(N log N)

Merge sort consistently performs at O(N log N) time complexity due to its recursive nature and the need to merge subarrays. Insertion sort has a best-case time complexity of O(N) when the array is already sorted, but it is generally O(N^2) for average and worst cases. By combining these algorithms, `DoKhanhSorter` leverages the strengths of both.

### Space Complexity

- **Space Complexity:** O(N)

The space complexity is O(N) because the algorithm uses a single temporary array of the same size as the input array to facilitate the merging process.

### Why a Certain Number Threshold

`DoKhanhSorter` uses a threshold to determine when to switch from merge sort to insertion sort. This threshold is chosen based on empirical performance testing and typically ranges from 10 to 20 elements. The rationale is that insertion sort is more efficient for small subarrays due to its lower overhead compared to merge sort.

## Notes on using Copilot (or other AI)

Copilot was used for generating ideas, debugging, and writing documentation in this README and in `DoKhanhSorter.java`.
