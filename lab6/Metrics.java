public class Metrics {
        public long comparison;
        public long cop;
        public long extraMemory;
        public long time;

        public void reset() {
            comparison = 0;
            cop = 0;
            extraMemory = 0;
            time = 0;
        }

        public double getTime() {
            return time / 1_000_000.0;
        }

        @Override
        public String toString() {
            return "comparisons=" + comparison +
                    ", copies=" + cop +
                    ", extraMemory=" + extraMemory +
                    ", timeMs=" + getTime();
        }


}
