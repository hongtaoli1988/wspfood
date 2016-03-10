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

out.clear(); //清空缓存的内容。
out = pageContext.pushBody(); //参考API
response.setContentType("image/jpeg");
response.addHeader("pragma","NO-cache");
response.addHeader("Cache-Control","no-cache");
response.addDateHeader("Expries",0);
int width=60, height=20;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

// 获取图形上下文
Graphics g = image.getGraphics();
        
//生成随机类
Random random = new Random();
        

//设置背景色
int fc=200;
int bc=250;
if(fc>255) fc=255;
if(bc>255) bc=255;
int rColor=fc+random.nextInt(bc-fc);
int gColor=fc+random.nextInt(bc-fc);
int bColor=fc+random.nextInt(bc-fc);


//随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
int fc1=160;
int bc1=200;
if(fc1>255) fc1=255;
if(bc1>255) bc1=255;
int rColor1=fc1+random.nextInt(bc1-fc1);
int gColor1=fc1+random.nextInt(bc1-fc1);
int bColor1=fc1+random.nextInt(bc1-fc1);

 %>
<%

        

        //以下填充背景颜色
        g.setColor(new Color(rColor,gColor,bColor));
        g.fillRect(0, 0, width, height);
       //设置字体颜色
        
        Font font=new Font("Times New Roman",Font.PLAIN,18);
        g.setFont(font);
        
		g.setColor(new Color(rColor1,gColor1,bColor1));
		//随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        for (int i=0;i<155;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
		// 取随机产生的认证码(4位数字)
        String sRand="";
        for (int i=0;i<4;i++){
            String rand=String.valueOf(random.nextInt(10));
            sRand+=rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand,13*i+6,16);
        }

		// 将认证码存入SESSION
        session.setAttribute("random",sRand);
        g.dispose();
        ServletOutputStream outStream = response.getOutputStream();
        JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(outStream);
        encoder.encode(image);
        outStream.close();
   %>
