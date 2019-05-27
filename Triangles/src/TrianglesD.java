import static java.lang.Math.sqrt;

class Triangle {
    double sideA, sideB, sideC;
    boolean markAsStr = false;
    double perimeter;
    double area;

    Triangle(Point A, Point B, Point C) {
        sideA = sqrt(Math.pow((B.x - A.x), 2) + Math.pow((B.y - A.y), 2));
        sideB = sqrt(Math.pow((C.x - B.x), 2) + Math.pow((C.y - B.y), 2));
        sideC = sqrt(Math.pow((A.x - C.x), 2) + Math.pow((A.y - C.y), 2));

        if (((B.x - A.x)*(C.x - B.x)+(B.y - A.y)*(C.y - B.y))==0||((C.x - B.x)*(A.x - C.x)+(C.y - B.y)*(A.y - C.y)==0)||((A.x - C.x)*(B.x - A.x)+(A.y - C.y)*(B.y - A.y)==0)){
            markAsStr=true;
        }
    }

    double getPerimeter() {
        return sideA + sideB + sideC;
    }

    double getArea() {
        return sqrt((sideA + sideB + sideC) * (sideB + sideC - sideA) * (sideA + sideC - sideB) * (sideA + sideB - sideC));
    }

    boolean isEquilateral() {
        return ((sideA == sideB) && (sideB == sideC) && (sideA == sideC));
    }
    boolean isStraight(){
        return (sideA*sideB/2==getArea()||sideB*sideC/2==getArea()||sideC*sideA/2==getArea());
    }
    boolean isIsosceles(){
        return ((sideA==sideB)||(sideB==sideC)||(sideA==sideC));
    }

}



public class TrianglesD {
    public static void main(String[] args) {
        int n = 8;
        Triangle[] tr = new Triangle [n];
        Triangle[] Straight = new Triangle [n];
        Point [] p=new Point[3];
        int countStraight=0,countEquilateral=0,countIsosceles=0,countRand=0;

        tr[0]=new Triangle(p[0] = new Point(0,-2),p[1] = new Point(-2,4), p[2] = new Point(2,4)); //Iso
        tr[1]=new Triangle(p[0] = new Point(4,-2),p[1] = new Point(3,3), p[2] = new Point(8,2)); //Iso
        tr[2]=new Triangle(p[0] = new Point(-2,-2),p[1] = new Point(-4,0), p[2] = new Point(-2,2)); //Is+STr
        tr[3]=new Triangle(p[0] = new Point(0,0),p[1] = new Point(0,4), p[2] = new Point(3,0));  //str
        tr[4]=new Triangle(p[0] = new Point(-5,0),p[1] = new Point(-1,9), p[2] = new Point(1,7));  //ran
        tr[5]=new Triangle(p[0] = new Point(-2,0),p[1] = new Point(-9,7), p[2] = new Point(2,4));  //str
        tr[6]=new Triangle(p[0] = new Point(2,5),p[1] = new Point(5, 5+sqrt(27)), p[2] = new Point(8,5)); //eq+is
        tr[7]=new Triangle(p[0] = new Point(-4,1),p[1] = new Point(0,6), p[2] = new Point(2,-3));  //ran

        for (int i = 0; i<n;i++){
            System.out.println((i+1)+" P="+tr[i].getPerimeter()+" S="+tr[i].getArea());
        }



        for (int i = 0; i<n;i++){
            if (tr[i].isEquilateral()) {
                countEquilateral++;
                countIsosceles++;
                continue;
            }
            if(tr[i].isIsosceles()&&tr[i].markAsStr){
                countIsosceles++;
                countStraight++;
                continue;
            }

            if (tr[i].isIsosceles()) {
                countIsosceles++;
                continue;
            }

            if (tr[i].markAsStr) {
                countStraight++;
            }

            else countRand++;
        }
        System.out.print("Равносторонних: "+countEquilateral+"\nРавнобедренных: "+countIsosceles+"\nПрямоугольных: "+countStraight+"\nПроизвольных: "+countRand);
        Triangle[] equi = new Triangle [countEquilateral];
        Triangle[] isos = new Triangle [countIsosceles];
        Triangle[] str = new Triangle [countStraight];
        Triangle[] ran = new Triangle [countRand];
        int ce=countEquilateral;
        int ci=countIsosceles;
        int cs=countStraight;;
        int cr=countRand;
        for (int i = 0; i<n;i++){
            if (tr[i].isEquilateral()) {
                equi[countEquilateral-ce]=tr[i];
                ce--;
                isos[countIsosceles-ci]=tr[i];
                ci--;
                continue;
            }
            if(tr[i].isIsosceles()&&tr[i].markAsStr) {
                isos[countIsosceles-ci]=tr[i];
                ci--;
                str[countStraight-cs]=tr[i];
                cs--;
                continue;
            }
            if (tr[i].isIsosceles()) {
                isos[countIsosceles-ci]=tr[i];
                ci--;
                continue;
            }

            if (tr[i].markAsStr) {
                str[countStraight-cs]=tr[i];
                cs--;

            }
            else {
                ran[countRand - cr] = tr[i];
                cr--;
            }
            }

        MinMax mm = new MinMax();

        System.out.print("\nНаименьшая площадь равностороннего: "+(mm.Min(equi)).getArea()+"\nНаибольшая площадь равностороннего:"+(mm.Max(equi)).getArea());
        System.out.print("\nНаименьшая площадь равнобедренного: "+(mm.Min(isos)).getArea()+"\nНаибольшая площадь равнобедренного:"+(mm.Max(isos)).getArea());
        System.out.print("\nНаименьшая площадь прямоугольного: "+(mm.Min(str)).getArea()+"\nНаибольшая площадь прямоугольного:"+(mm.Max(str)).getArea());
        System.out.print("\nНаименьшая площадь произвольного: "+(mm.Min(ran)).getArea()+"\nНаибольшая площадь произвольного:"+(mm.Max(ran)).getArea());

        }



}
