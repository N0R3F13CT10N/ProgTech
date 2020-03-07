package task2;

public class Time {
    private int sec;
    private int min;
    private int hr;

    public Time(){
        sec = 0;
        min = 0;
        hr = 0;
    }

    public Time(int h, int m, int s){
        sec = s;
        min = m;
        hr = h;
    }

    public int total_seconds(){
        return sec + min * 60 + hr * 3600;
    }

    public void plus_5_sec(){
        if(sec < 55)
            sec += 5;
        else{
            min += 1;
            sec = (sec + 5) % 60;
            if(min == 60) {
                min = 0;
                hr++;
            }
            if(hr == 24)
                hr = 0;
        }
    }

    @Override
    public String toString() {
        return hr + ":" + min + ":" + sec;
    }
}
