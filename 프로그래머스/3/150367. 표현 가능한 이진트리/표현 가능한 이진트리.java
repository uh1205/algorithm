class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            // 이진수로 변환
            String binaryString = Long.toBinaryString(numbers[i]);

            // 포화 이진트리 형태로 만들기 (앞에 0 채워 넣기)
            String fullBinaryTree = getFullBinaryTree(binaryString);

            // 이진트리 유효성 검사
            answer[i] = canBeTree(fullBinaryTree) ? 1 : 0;
        }

        return answer;
    }

    String getFullBinaryTree(String binaryString) {
        int treeHeight = 1;
        int nodeCount = 1;

        // 트리의 높이가 증가할수록 노드의 개수는 1, 3, 7, ...
        while (nodeCount < binaryString.length()) {
            treeHeight++;
            nodeCount = (1 << treeHeight) - 1;
        }

        // 앞에 추가해야 할 0의 개수
        int paddingCount = nodeCount - binaryString.length();

        // 기존 이진수의 앞에 0 추가하기
        StringBuilder sb = new StringBuilder();
        while (paddingCount-- > 0) {
            sb.append('0');
        }
        sb.append(binaryString);

        return sb.toString();
    }

    boolean canBeTree(String binaryString) {
        // 노드가 하나일 경우 항상 유효
        if (binaryString.length() == 1) return true;

        int mid = binaryString.length() / 2;
        char root = binaryString.charAt(mid);

        String leftSubtree = binaryString.substring(0, mid);
        String rightSubtree = binaryString.substring(mid + 1);

        // 부모가 0이면 자식은 1이 될 수 없음 (자식 서브트리에 실제 노드가 있는지 확인)
        if (root == '0' && (leftSubtree.contains("1") || rightSubtree.contains("1"))) {
            return false;
        }

        // 왼쪽과 오른쪽 서브트리에 대해 재귀적으로 검사
        return canBeTree(leftSubtree) && canBeTree(rightSubtree);
    }
}