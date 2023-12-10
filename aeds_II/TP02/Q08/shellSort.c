
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void shellsort(int v[], int n);

int main() {
    srand(time(NULL));
    int N;
    printf("Input N: ");
    scanf("%d", &N);
    int *a = (int *)malloc(N * sizeof(int));
    int i = 0;

    printf("Enter masiv: ");
    for (; i < N; i++) {
        a[i] = rand() % 100;
        printf("%d ", a[i]);
    }

    shellsort(a, N);

    printf("\n");
    printf("Result: ");
    for (i = 0; i < N; i++) {
        printf("%d ", a[i]);
    }
    printf("\n");

    return 0;
}

void shellsort(int v[], int n) {
    int i, j, gap, temp;
    int num = 0;

    for (gap = n / 2; gap > 0; gap /= 2) {
        for (i = gap; i < n; i++)
            for (j = i - gap; j >= 0 && v[j] > v[j + gap]; j -= gap) {
                temp = v[j];
                v[j] = v[j + gap];
                v[j + gap] = temp;
                num++;
            }
    }
    printf("\n");
    printf("Number of iterations: %d\n", num);
}