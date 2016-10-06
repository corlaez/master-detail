package jarmandocordova.masterdetail.global.data.belatrixsf;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarma on 10/5/2016.
 */
public class Data {

    private String period;
    private String myWhy;
    private String dream;
    private String roadmap;
    private List<Metric> metric = new ArrayList<>();
    private List<Volume> volume = new ArrayList<>();

    @Override
    public String toString() {
        return "Data{" +
                "period='" + period + '\'' +
                ", myWhy='" + myWhy + '\'' +
                ", dream='" + dream + '\'' +
                ", roadmap='" + roadmap + '\'' +
                ", metric=" + metric +
                ", volume=" + volume +
                '}';
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getMyWhy() {
        return myWhy;
    }

    public void setMyWhy(String myWhy) {
        this.myWhy = myWhy;
    }

    public String getDream() {
        return dream;
    }

    public void setDream(String dream) {
        this.dream = dream;
    }

    public String getRoadmap() {
        return roadmap;
    }

    public void setRoadmap(String roadmap) {
        this.roadmap = roadmap;
    }

    public List<Metric> getMetric() {
        return metric;
    }

    public void setMetric(List<Metric> metric) {
        this.metric = metric;
    }

    public List<Volume> getVolume() {
        return volume;
    }

    public void setVolume(List<Volume> volume) {
        this.volume = volume;
    }

    public class Metric {

        private String type;
        private String value;
        private String yoy;
        private String goal;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getYoy() {
            return yoy;
        }

        public void setYoy(String yoy) {
            this.yoy = yoy;
        }

        public String getGoal() {
            return goal;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }

        @Override
        public String toString() {
            return "Metric{" +
                    "\n  type='" + type + '\'' +
                    ",\n  value='" + value + '\'' +
                    ",\n  yoy='" + yoy + '\'' +
                    ",\n  goal='" + goal + '\'' + "\n" +
                    '}';
        }
    }

    public class Volume {

        private String type;
        private String value;
        private String minRequired;
        private String targetAmount;
        private String volumeTarget;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getMinRequired() {
            return minRequired;
        }

        public void setMinRequired(String minRequired) {
            this.minRequired = minRequired;
        }

        public String getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(String targetAmount) {
            this.targetAmount = targetAmount;
        }

        public String getVolumeTarget() {
            return volumeTarget;
        }

        public void setVolumeTarget(String volumeTarget) {
            this.volumeTarget = volumeTarget;
        }

        @Override
        public String toString() {
            return "Volume{" +
                    "\n  type='" + type + '\'' +
                    ",\n  value='" + value + '\'' +
                    ",\n  minRequired='" + minRequired + '\'' +
                    ",\n  targetAmount='" + targetAmount + '\'' +
                    ",\n  volumeTarget='" + volumeTarget + '\'' + "\n" +
                    '}';
        }
    }
}

