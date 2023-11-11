/*
Madhur Jaripatke
Roll No. 55
TE A Computer
RMDSSOE, Warje, Pune
*/
/*
Design suitable Data structures and implement Pass-I and Pass-II of a two-pass assembler for
pseudo-machine. Implementation should consist of a few instructions from each category and
few assembler directives. The output of Pass-I (intermediate code file and symbol table)
should be input for Pass-II.
*/
/*
Pass 2 Assembler
*/

import java.text.DecimalFormat;
class symtab{
	int index;
	String name;
	int addr;
	
	symtab(int i,String s,int a){
		index = i;
		name = s;
		addr = a;
	}
}
class littab{
	int index;
	String name;
	int addr;

	littab(int i,String s,int a){
		index = i;
		name = s;
		addr = a;
	}
	
	void setaddr(int a){
		addr = a;
	}
}

public class Pass2Assembler {
	public static void main(String[] args) {
			String ic[][] = {{"(AD,01)",null,"(C,100)"},
							{"(IS,04)","(RG,01)","(L,0)"},
							{"(IS,01)","(RG,03)","(L,1)"},
							{"(DL,01)",null,"(C,3)"},
							{"(IS,04)","(RG,01)","(S,2)"},
							{"(IS,01)","(RG,01)","(S,3)"},
							{"(IS,05)","(RG,01)","(S,4)"},
							{"(DL,02)",null,"(C,5)"},
							{"(DL,02)",null,"(C,1)"},
							{"(AD,04)",null,"(C,103)"},
							{"(IS,10)",null,"(S,4)"},
							{"(AD,03)",null,"(C,101)"},
							{"(IS,02)","(RG,01)","(L,2)"},
							{"(IS,03)","(RG,03)","(S,2)"},
							{"(DL,02)",null,"(C,5)"},
							{"(AD,03)",null,"(C,111)"},
							{"(IS,00)",null,null},
							{"(DL,02)",null,"(C,19)"},
							{"(AD,02)",null,null},
							{"(DL,02)",null,"(C,1)"}};
			
			Symtab s[] = new Symtab[20];
			Littab l[] = new Littab[20];
			
			s[0] = new Symtab(0,"A",102);
			s[1] = new Symtab(1,"L1",105);
			s[2] = new Symtab(2,"B",112);
			s[3] = new Symtab(3,"C",103);
			s[4] = new Symtab(4,"D",103);
			
			l[0] = new Littab(0,"='5'",108);
			l[1] = new Littab(1,"='1'",109);
			l[2] = new Littab(2,"='1'",113);
			
			int i=0,j=0,ind=0;
			String m,op1,op2,temp;
			char arr1[],arr2[],arr3[];
			
			DecimalFormat df = new DecimalFormat("000");
			
			while(i < ic.length){
				temp = null;
				arr1 = null;
				arr2 = null;
				arr3 = null;
				
				m = ic[i][0];
				op1 = ic[i][1];
				op2 = ic[i][2];
				
				arr1 = m.toCharArray();
				if(op1 != null){
					arr2 = op1.toCharArray();
				}
				if(op2 != null){
					arr3 = op2.toCharArray();
				}
				if(arr1[1] == 'I' && arr1[2] == 'S'){
					System.out.print(arr1[4]+""+arr1[5]+"\t");
					if(op1 != null){
						System.out.print(arr2[4]+""+arr2[5]+"\t");
					}else{
						System.out.print("00"+"\t");
					}
					if(op2 != null){
						if(arr3[1] == 'R' && arr3[2] == 'G'){
							System.out.print(arr3[4]+arr3[5]+"\t");
						}
						else if(arr3[1] == 'S'){
							ind = Character.getNumericValue(arr3[3]);
							j=4;
							while(arr3[j] != ')'){
								ind = ind*10;
								ind = ind + (Character.getNumericValue(arr3[j]));
								j++;
							}
							System.out.print(s[ind].addr+"\t");
						}
						else if(arr3[1] == 'L'){
							ind = Character.getNumericValue(arr3[3]);
							j=4;
							while(arr3[j] != ')'){
								ind = ind*10;
								ind = ind + (Character.getNumericValue(arr3[j]));
								j++;
							}
							System.out.print(l[ind].addr+"\t");
						}
					}else{
						System.out.print("000"+"\t");
					}
				}
				else if(arr1[1] == 'D' && arr1[2] == 'L'){
					if(arr1[5] == '2'){
						System.out.print("00\t00\t");
						j=3;
						while(arr3[j] != ')'){
							if(temp == null)
								temp = String.valueOf(arr3[j]);
							else
								temp = temp.concat(String.valueOf(arr3[j]));
							j++;
						}
						System.out.print(df.format(Integer.parseInt(temp)));
					}
				}
				i++;
				System.out.print("\n");
			}
			
	}

}