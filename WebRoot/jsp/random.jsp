<%@ page autoFlush="false"  import="java.util.*,java.awt.*,java.awt.image.*,com.sun.image.codec.jpeg.*,java.util.*" %>

<%
//set Chinese Char 
//Cody by JarryLi@gmail.com;
//homepage:jiarry.126.com
request.setCharacterEncoding("GBK");
response.setCharacterEncoding("GBK");
response.setContentType("text/html; charset=GBK");
%>
<%

out.clear(); //��ջ�������ݡ�
out = pageContext.pushBody(); //�ο�API
response.setContentType("image/jpeg");
response.addHeader("pragma","NO-cache");
response.addHeader("Cache-Control","no-cache");
response.addDateHeader("Expries",0);
int width=60, height=20;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

// ��ȡͼ��������
Graphics g = image.getGraphics();
        
//���������
Random random = new Random();
        

//���ñ���ɫ
int fc=200;
int bc=250;
if(fc>255) fc=255;
if(bc>255) bc=255;
int rColor=fc+random.nextInt(bc-fc);
int gColor=fc+random.nextInt(bc-fc);
int bColor=fc+random.nextInt(bc-fc);


//�������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
int fc1=160;
int bc1=200;
if(fc1>255) fc1=255;
if(bc1>255) bc1=255;
int rColor1=fc1+random.nextInt(bc1-fc1);
int gColor1=fc1+random.nextInt(bc1-fc1);
int bColor1=fc1+random.nextInt(bc1-fc1);

 %>
<%

        

        //������䱳����ɫ
        g.setColor(new Color(rColor,gColor,bColor));
        g.fillRect(0, 0, width, height);
       //����������ɫ
        
        Font font=new Font("Times New Roman",Font.PLAIN,18);
        g.setFont(font);
        
		g.setColor(new Color(rColor1,gColor1,bColor1));
		//�������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
        for (int i=0;i<155;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
		// ȡ�����������֤��(4λ����)
        String sRand="";
        for (int i=0;i<4;i++){
            String rand=String.valueOf(random.nextInt(10));
            sRand+=rand;
            // ����֤����ʾ��ͼ����
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
            g.drawString(rand,13*i+6,16);
        }

		// ����֤�����SESSION
        session.setAttribute("random",sRand);
        g.dispose();
        ServletOutputStream outStream = response.getOutputStream();
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(outStream);
        encoder.encode(image);
        outStream.close();
   %>
