package adaline;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Adaline {
    
    public static void main(String[] args) {
        double x1_datapelatihan []={0,1,2,2,4,-2,-1,-2,-1,1};
        double x2_datapelatihan []={4,4,3,2,1,4,3,2,1,-2};
        double t_datapelatihan  []={1,1,1,1,1,-1,-1,-1,-1,-1};
        double y_in_datapelatihan[]={0,0,0,0,0,0,0,0,0,0};
        double y_out_datapelatihan []={0,0,0,0,0,0,0,0,0,0};
        
        
        double x1_datapengujian []={2,1,3,2,-1,1,-2,-3,2,3};
        double x2_datapengujian []={2,3,1,1,5,-1,3,3,-3,-4};
        double t_datapengujian  []={1,1,1,1,1,-1,-1,-1,-1,-1};
        double y_in_datapengujian []={0,0,0,0,0,0,0,0,0,0};
        double y_out_datapengujian []={0,0,0,0,0,0,0,0,0,0};    
        
        String Hasil []= {"","","","","","","","","",""};
        
        double wi_lama =0;
        double wi_baru =0;
        double w_akhir =0;
        double alpa = 0;
        
        
        boolean result = true;
        int epoch=1;
        DecimalFormat df = new DecimalFormat("#.###");
        
       
        System.out.println("                   Data Pelatihan              ");
        System.out.println("");
        System.out.println("|\tx1\t|\tx2\t|      Target\t|");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < x1_datapelatihan.length; i++) {
            System.out.println("|\t"+x1_datapelatihan[i]+"\t|\t"+x2_datapelatihan[i]+"\t|\t"+t_datapelatihan[i]+"\t|");
        }
       
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.print("Nilai Alpha= ");
        alpa = input.nextDouble();
        
        
        
        System.out.println("");
        System.out.println("               Data Training       ");
        System.out.println("");
        while(result){
            System.out.println("Epoch ke - "+epoch);
            System.out.println("|x1\t|x2\t|y_in\t\t     Hasil\t");

            
            
            for (int i = 0; i < y_in_datapelatihan.length; i++) {
                if (Hasil[i].equals("Benar")) {
                }else{
                    y_in_datapelatihan[i] = w_akhir + (wi_lama*x1_datapelatihan[i]) + (wi_baru*x2_datapelatihan[i]);

                   double wi_lama_awal = wi_lama;
                   double wi_baru_awal = wi_baru;

                    wi_lama=wi_lama+alpa*x1_datapelatihan[i]*(t_datapelatihan[i]-y_in_datapelatihan[i]);
                    wi_baru=wi_baru+alpa*x2_datapelatihan[i]*(t_datapelatihan[i]-y_in_datapelatihan[i]);
                    w_akhir=w_akhir+alpa*(t_datapelatihan[i]-y_in_datapelatihan[i]);


                    if (((wi_lama-wi_lama_awal) > (wi_baru-wi_baru_awal) || (wi_lama-wi_lama_awal)<=0 )&&((wi_baru-wi_baru_awal) > (wi_lama-wi_lama_awal) || (wi_baru-wi_baru_awal)<=0 )) {
                        Hasil[i]="Benar";
                    }else{
                        Hasil[i]="Salah";
                    }
                }    
                System.out.println("|"+x1_datapelatihan[i]+"\t|"+x2_datapelatihan[i]+"\t|"+df.format(y_in_datapelatihan[i])+"\t" + "\t"+Hasil[i]+"\t");
                
            }
            
            if(Hasil[0].equals("Benar")&&Hasil[1].equals("Benar")&&Hasil[2].equals("Benar")&&Hasil[3].equals("Benar")&&Hasil[4].equals("Benar")&&Hasil[5].equals("Benar")&&Hasil[6].equals("Benar")&&Hasil[7].equals("Benar")&&Hasil[8].equals("Benar")&&Hasil[9].equals("Benar")) {
                result = false;
            }
            epoch=epoch+1;
            
        }
        System.out.println("Nilai bobot ideal: ");
        System.out.println("wi_lama (bobot x1)\t= "+wi_lama);
        System.out.println("wi_baru (bobot x2)\t= "+wi_baru);
        System.out.println("w_akhir (bobot bias)\t= "+w_akhir);
       
        
        
        
        
        
       
        System.out.println("");
        System.out.println("");
        System.out.println("                  Data Pengujian ");
        System.out.println("|\tx1\t|\tx2\t|\tt\t|");
        for (int i = 0; i < x1_datapelatihan.length; i++) {
            System.out.println("|\t"+x1_datapengujian[i]+"\t|\t"+x2_datapengujian[i]+"\t|\t"+t_datapengujian[i]+"\t|");
        }     
        
        
        
        System.out.println("");
        System.out.println("");
        System.out.println("                               Hasil Pengujian :");
        System.out.println("|x1\t|x2\t|wi_lama\t|wi_baru\t|w_akhir\t|y_in\t\t|y_out\t|target\t|Hasil\t|");

            for (int i = 0; i < y_in_datapengujian.length; i++) {
                y_in_datapengujian[i] = w_akhir + (wi_lama*x1_datapengujian[i]) + (wi_baru*x2_datapengujian[i]);
                if(y_in_datapengujian[i]<0){
                    y_out_datapengujian[i]=-1;
                }else{
                    y_out_datapengujian[i]=1;
                }
                if (y_out_datapengujian[i]!=t_datapengujian[i]) {
                    Hasil[i]="Salah";
                }else{
                    Hasil[i]="Benar";
                }
                System.out.println("|"+x1_datapengujian[i]+"\t|"+x2_datapengujian[i]+"\t|"+df.format(wi_lama)+"\t|"+df.format(wi_baru)+"\t|"+df.format(w_akhir)+"\t|"+df.format(y_in_datapengujian[i])+"\t|"+y_out_datapengujian[i]+"\t|"+t_datapengujian[i]+"\t|"+Hasil[i]+"\t|");
            }
            
            double error=0;
            for (int i = 0; i < t_datapengujian.length; i++) {
                error = error+Math.pow((t_datapengujian[i]-y_out_datapengujian[i]), 2);
                System.out.print(error);
            }
            System.out.println("");
            System.out.println("error : "+ error / t_datapengujian.length);
        
    }
}
