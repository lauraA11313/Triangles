public class MinMax {

        Triangle min;
        Triangle max;
        Triangle Min(Triangle [] objArr){
            min=objArr[0];
            for (int i = 1; i < objArr.length; i++) {
                if (min.getArea() > objArr[i].getArea())
                    min = objArr[i];
            }
            return min;
        }
        Triangle Max(Triangle [] objArr){
            max=objArr[0];
            for (int i = 1; i < objArr.length; i++) {
                if (max.getArea() < objArr[i].getArea())
                    max = objArr[i];
            }
            return max;
        }
    }
