
package com.rv.framwork.fyt.car;

public class FytCarBean {
    private SoundEffect soundEffect;
    private SurroundSound surroundSound;
    private BassIncrease bassIncrease;
    private SoundField soundField;
    private BassFilter bassFilter;

    public SoundEffect getSoundEffect() {
        return soundEffect;
    }

    public void setSoundEffect(SoundEffect soundEffect) {
        this.soundEffect = soundEffect;
    }

    public SurroundSound getSurroundSound() {
        return surroundSound;
    }

    public void setSurroundSound(SurroundSound surroundSound) {
        this.surroundSound = surroundSound;
    }

    public BassIncrease getBassIncrease() {
        return bassIncrease;
    }

    public void setBassIncrease(BassIncrease bassIncrease) {
        this.bassIncrease = bassIncrease;
    }

    public SoundField getSoundField() {
        return soundField;
    }

    public void setSoundField(SoundField soundField) {
        this.soundField = soundField;
    }

    public BassFilter getBassFilter() {
        return bassFilter;
    }

    public void setBassFilter(BassFilter bassFilter) {
        this.bassFilter = bassFilter;
    }

    public static class SoundEffect {
        private int eq_type;//EQ类型   0-1
        private int[] eq_gain;//EQ增益  分16和32组
        private int[] eq_q;//EQ：Q值  16组
        private int[] eq_fc;//EQ：FC值  16组
        private int eq_mode;//EQ：模式
        private int loud;//EQ：loud开关

/*        public SoundEffect(int eq_type, int[] eq_gain, int[] eq_q, int[] eq_fc, int eq_mode,int loud) {
            this.eq_type = eq_type;
            this.eq_gain = eq_gain;
            this.eq_q = eq_q;
            this.eq_fc = eq_fc;
            this.eq_mode = eq_mode;
            this.loud = loud;
        }*/

        public int getEq_type() {
            return eq_type;
        }

        public void setEq_type(int eq_type) {
            this.eq_type = eq_type;
        }

        public int[] getEq_gain() {
            return eq_gain;
        }

        public void setEq_gain(int[] eq_gain) {
            this.eq_gain = eq_gain;
        }

        public int[] getEq_q() {
            return eq_q;
        }

        public void setEq_q(int[] eq_q) {
            this.eq_q = eq_q;
        }

        public int[] getEq_fc() {
            return eq_fc;
        }

        public void setEq_fc(int[] eq_fc) {
            this.eq_fc = eq_fc;
        }

        public int getEq_mode() {
            return eq_mode;
        }

        public void setEq_mode(int eq_mode) {
            this.eq_mode = eq_mode;
        }

        public int getLoud() {
            return loud;
        }

        public void setLoud(int loud) {
            this.loud = loud;
        }
    }

    public static class SurroundSound {
        private boolean state;//状态
        private int levelfl;//前左 等级
        private int levelfr;//前右 等级
        private int levelrl;//后左 登记
        private int levelrr;//后右 等级
        private int gain;//后喇叭环绕强度 增益

/*        public SurroundSound(boolean state, int levelfl, int levelfr, int levelrl, int levelrr,int gain) {
            this.state = state;
            this.levelfl = levelfl;
            this.levelfr = levelfr;
            this.levelrl = levelrl;
            this.levelrr = levelrr;
            this.gain = gain;
        }*/

        public boolean getState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public int getLevelfl() {
            return levelfl;
        }

        public void setLevelfl(int levelfl) {
            this.levelfl = levelfl;
        }

        public int getLevelfr() {
            return levelfr;
        }

        public void setLevelfr(int levelfr) {
            this.levelfr = levelfr;
        }


        public int getLevelrl() {
            return levelrl;
        }

        public void setLevelrl(int levelrl) {
            this.levelrl = levelrl;
        }


        public int getLevelrr() {
            return levelrr;
        }

        public void setLevelrr(int levelrr) {
            this.levelrr = levelrr;
        }


        public int getGain() {
            return gain;
        }

        public void setGain(int gain) {
            this.gain = gain;
        }
    }

    public static class BassIncrease {
        private int bassgain_front;//低音加强 前
        private int bassgain_rear;//低音加强  后
        private int bassfreq_front;//低音加强 频率前
        private int bassfreq_rear;//低音加强  频率后

/*        public BassIncrease(int bassgain_front, int bassgain_rear, int bassfreq_front, int bassfreq_rear) {
            this.bassgain_front = bassgain_front;
            this.bassgain_rear = bassgain_rear;
            this.bassfreq_front = bassfreq_front;
            this.bassfreq_rear = bassfreq_rear;
        }*/

        public int getBassgain_front() {
            return bassgain_front;
        }

        public void setBassgain_front(int bassgain_front) {
            this.bassgain_front = bassgain_front;
        }

        public int getBassgain_rear() {
            return bassgain_rear;
        }

        public void setBassgain_rear(int bassgain_rear) {
            this.bassgain_rear = bassgain_rear;
        }

        public int getBassfreq_front() {
            return bassfreq_front;
        }

        public void setBassfreq_front(int bassfreq_front) {
            this.bassfreq_front = bassfreq_front;
        }

        public int getBassfreq_rear() {
            return bassfreq_rear;
        }

        public void setBassfreq_rear(int bassfreq_rear) {
            this.bassfreq_rear = bassfreq_rear;
        }
    }

    public static class SoundField {
        private int balfade_x;//声场设置X
        private int balfade_y;//声场设置Y

/*        public SoundField(int balfade_x, int balfade_y) {
            this.balfade_x = balfade_x;
            this.balfade_y = balfade_y;
        }*/

        public int getBalfade_x() {
            return balfade_x;
        }

        public void setBalfade_x(int balfade_x) {
            this.balfade_x = balfade_x;
        }

        public int getBalfade_y() {
            return balfade_y;
        }

        public void setBalfade_y(int balfade_y) {
            this.balfade_y = balfade_y;
        }
    }

    public static class BassFilter {
        private int freq_front;//低音过滤  前
        private int freq_rear;//低音过滤   后

        public BassFilter() {

        }

        public int getFreq_front() {
            return freq_front;
        }

        public void setFreq_front(int freq_front) {
            this.freq_front = freq_front;
        }

        public int getFreq_rear() {
            return freq_rear;
        }

        public void setFreq_rear(int freq_rear) {
            this.freq_rear = freq_rear;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "soundEffect='" + soundEffect + '\'' +
                ", surroundSound=" + surroundSound +
                ", bassIncrease=" + bassIncrease +
                ", soundField=" + soundField +
                ", bassFilter=" + bassFilter +
                '}';
    }
}
