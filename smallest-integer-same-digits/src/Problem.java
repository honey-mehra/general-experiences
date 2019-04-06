public class Problem {

    public int solution(int N) {
        if(N < 10) return 0;

        int answer = 1;
        while( (N/10) > 0) {
            answer = answer * 10;
            N = N/10;
        }
        return answer;
    }
}
