/**  
 * All rights Reserved, Designed By www.cyunsoft.com
 * @Title:  Other.java   
 * @Package com.test   
 * @Description:    描述   
 * @author: lsq     
 * @date:   2019年11月22日 上午10:41:44   
 * @version V1.0 
 * @Copyright:江苏稠云 www.cyunsoft.com 
 */
package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author lsq
 *
 */
public class Other {
	/**
     * 字符串转图片
     * @param imgStr --->图片字符串
     * @param filename    --->图片名
     * @return
     */
    public static boolean generateImage(String imgStr, String filename) {
        
        if (imgStr == null) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(imgStr);
            // 处理数据
            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream("D:/"+filename);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 图片转字符串
     * @param filePath    --->文件路径
     * @return
     */
    public static String getImageStr(String filePath) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(filePath);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    
    /*
     * 测试代码
     */
    public static void main(String[] args) {
        String imageStr11 = getImageStr("D:\\3.png");
        System.out.println(imageStr11);
        String ssString ="G8AAABRCAYAAADctfi9AAAFmElEQVR4nO2dTUhVQRTHp+ibwkhJ\r\n" + 
        		"gidKkZUgZbSxnVSbKGhRuxYtjLBNBG2CFi2kTWGLoIhqIbSIaCEouZQ2USAZJEYRkvlMDPuQ7MM+\r\n" + 
        		"qPd/8aCo3pyZe2beOZf7B0lkmjc//3dmzsycuc77UZDJpFLzK92ATP5aELLyz+Pjxa9Po6Pm47Nn\r\n" + 
        		"xZ+96un5q9ziXM5UbdtW/H7Fli1maUND8V+pksI1j3vYBNT07dvm3b175v3QUKK6VrW1mdoDB8zK\r\n" + 
        		"7duZWucviVxs5r1/+NC87O42bwYGOKr7Q3iCc+3tZvW+fex12ySZK7F5X1+/Ni8uXPjnsMEtwK7v\r\n" + 
        		"7IwypGrgSmTeu7t3zeiZM2Yun/etwkt1HR0md+RIsPq1cHmbN3n9unl+7pzPf2UR5o0N58+z16uJ\r\n" + 
        		"y8u8/OXLZvzSJeeGcYvbQG1czus8DCkSACEEEVy9RCOXU89DuDy0d69zYxBNYTJe3tRkFlZXF79+\r\n" + 
        		"18enT82X6Wkz++iRedXb6zzXrDt9OlEkqpXLybwnx487hcyYgGv37/8LyiZEePmrV51gWwq/nCV1\r\n" + 
        		"dU6fU5JWLvKwiWGFCogxGx+KyMkVEMLTtrWvz6k3TVy75vw5kGYusnn5K1dI5fBUYrL17QW/C8MG\r\n" + 
        		"6qMITzWGKVdp5iKZh10GypYQnkzu9Rfqo4K67oJo5yKZN3P/vrUMdglCrLsggK5oabGWc40WtXOR\r\n" + 
        		"zEOkZG1IezulKm+tPXmSVA69iSrtXFbzMN7aoiM8naE3jZc1NhaHL5tmh4dJ9aWBy2re7MiIteLV\r\n" + 
        		"HmskH9Xs3m0tUzpfsykNXPaeR/hlLG9utpbh0NL6emuZT2NjpLrSwGU1b25iwlrxopoaaxkOYYix\r\n" + 
        		"iXpQmgYuq3mU8Jvy4dKUBi5rDktrIcopLRLRdb9/+GC+TE2ZuclJ821mJsgJcwylgYuUgFR6AqU/\r\n" + 
        		"ia7SzqUq9Y+y/UUJu6XJl0uVeZTwfkFVVYSW8MqXS5V5b+/csZaRnO/5P/lyqTEPB6aUIAKJrZqU\r\n" + 
        		"hEuNeUh4pUhbz0vCpcI85FBSTgzWHDwYoTV8Ssqlwjwkv1JUvXNn4JbwKimXePOQpkDJWsa5mKYh\r\n" + 
        		"k4NLtHkYVpC5TFHu8OHAreETF5do80Y7O0mZVng6JdwkooqLS6x5SDql7i/WHzsWuDV84uQSaR7S\r\n" + 
        		"znFngCJEYlrmOm4u9suVSeVy0QNpCsiD1KAQXKJ6HiIwl7sHG7u6AraGT6G4xJgHwMdHj5LLI3FV\r\n" + 
        		"w1FOSC4R5mG94wKI+aASV5xdFZor6NsgKHK9E4dzrYYTJwK2iEcxuCra83wAQ2UvcyoWV8XMy4z7\r\n" + 
        		"pSRcFRk2Xe/DaTEuNlf0npcZ90scXNF6Hk6MRzo6nG6FajCuklxReh6yo9JoXKW5gvc810UqFPol\r\n" + 
        		"ORySwBXUPJ/3mmgwTgpXMPNcJ3Co8exZU71rV6AW8UgSF7t5mMDHurqcATddvCj6QFUiF6t5PpEX\r\n" + 
        		"jj+aCkMQx1sWQkkqF1u0iTvTPpFXc3e3aOMkc7H0PJ/ICzvo0jeYpXMlNs8HEHDSE2Q1cCUyzwdQ\r\n" + 
        		"emACaeHynvMwiVNzDyFM4Jtv3hRvnCYuL/Ncoy/kHyLykp62oI3La9jEeocKqGGPsiRtXM7mYWuI\r\n" + 
        		"ulDVEFGWpJHLKW8Ta57hQ4dIZTXsUZaklcvJvAd79pCGFUmAFGnlor8stTCsUAAxpEgCtEkzF6nn\r\n" + 
        		"4UrS4I4d1sokTOIu0s5F6nlTt25Zy2C9s/bUqcQNiintXNaeR306panV8tLUNHBZe950f3/QxlRK\r\n" + 
        		"aeCymjd540aMdkRXGrjKmof1T+y/ZBVDaeEqax71fc3alBausuZR39esTWnhKmvezOBgrHZEVVq4\r\n" + 
        		"ypqXhnnhX0oLl4ibsZn8JO5tEJnoynqeYmXmKVZmnmJl5ilWZp5iZeYp1k/0JQYSfHbW9g\r\n";
        
        
    	String imageStr="iVBORw0KGgoAAAANSUhEUgAAAFcAAABFCAYAAAA/1nr8AAAR3UlEQVR4nOVcS4ylVRGu89//dvft\r\n" + 
    			"1/Q8et5PYgTlMQxMBEYFJAEicaLEHYkxujJRY+LCFTsXrExcsHHtzpi4YAGYCIPAiAiI6AADzDAj\r\n" + 
    			"PT3Mo7un3697/+N5n6o65+/bo7CY9k9u3/+86lR9Vaeqzvn/22JhYVmCuvQfAXWXcD1wP+HKkoyT\r\n" + 
    			"aASw+v83+qUv6M/rf3sDXjjxEhRFAULUk1rvJYPaGJtmUhGapK9DguAKSW4wPYnm4ZcgfdJ+ks5f\r\n" + 
    			"y39Sm9IxfSMvVVXB97/3BJQYwvGLF+HES69A2WxCoYTHNHLazYuQqZPSCSACE5ghXpYZybLjJFYE\r\n" + 
    			"FVrKDGSSKyOOkRHpWJK4ntGTfIwtV5WETnsVvvv4cSh9mwZTW2yzp8d8rOWux3ojw97aCWiCSEM0\r\n" + 
    			"TPvmBIJkXHLvEE4UGTGhhHydwLxIqkxEz9LiikKKZHNrq9U4lGVp3YK+Kmm9jxDaJTQU0NfvFvyE\r\n" + 
    			"QmAP5RlD3IDzCtLYcqy1FabOlBELhqQM9h/GmL4CsIRWn8JKjb2bB9N6JGlAsEYhgxLMMGHnw/RF\r\n" + 
    			"8KaSjhGOTelp2kk1jiWaOjAdCQE01aeNeXdXo7DfnSr1TbKhB1Ewg/ZlBNJDRJapBxNqrFvSfpge\r\n" + 
    			"mQfzQyw99iWuw9VVyNKzboC5jYQ/z4P6lMhheM8VoH2oKeGJPgnvtAU8vSCgclKPbgP42Y8rM+DX\r\n" + 
    			"Twu4MgFezTB/zzIsHVmG3veaMHCiFwCtuLiEsWvgAYIyHMb4ssgDSsFArgLPjcAlrgsBJ5kbo7yg\r\n" + 
    			"P4yHQL+K8hQOUTKR/mh/8aOWhJ3KQh/pkbC/EeV4+KEKDh2UcOiQhOPfks4ClCNvdWDh2BJ0RipY\r\n" + 
    			"PLoCncHKOHjpfJGeuHKCxQ+Rwgqu+/p2P8az5ugpgknfON7fWr4qWSEeLB+eIOEJWYJ0oHoeTXXl\r\n" + 
    			"6fv6yhmjdFlC5EFfyC1gkKVxBR92BBxWKF9TzF5FGjl33smm7s9+LMM4sazcxVQB7Z0dKGaVCSxG\r\n" + 
    			"ATF+dD5kBcGi6dKO2YGkZdIe81bcB2cWnF4SppIsBBIrp6suw0sAWzJwaS/45RzAzSpZG+tImK3i\r\n" + 
    			"4JOvAVwYt4FBgxuUtQIw/NsWrO7qQOOy8tzLWECRLDeyHJmfS8HBCuICeWIyWFxssHMngEoKbqQp\r\n" + 
    			"8nNiPmtcjrmr7EdfKbiOQbWgYUHd/H011ntqHTX47FnKfwBnSWns4wItMwpBDlDPIElvWK6TS/SD\r\n" + 
    			"/yTfeAyhnvhZCpqvrzCcKaBQs3I8blVlPvoi2UIA0OYaXD0ZraURnwDKgOWRnm8aODg5SCXiKQZE\r\n" + 
    			"ClqWJ8IKAg7LGPjnSx/NiXnHWYfDx8cYfZXA2PdjRIVMPcjKUpAoBgOUL7G8oAk4oQzJJYGCHw0b\r\n" + 
    			"0xeIPk3xUrBcPeELpXqeHlqxRB6PB/pw+RK3YDWio2ARyxgIzrjjj24G/B8fhRl9RI+ChS0oBYKP\r\n" + 
    			"8e1xVJUs2dAnsW6uBJkBvyIrwdJHlkqAx5PZWwKuXxLatNVOmGoJahiU9EyIgMEACsxITi+11mTO\r\n" + 
    			"XKYgkUXjPhl+E2Um9OkKxJRCyiij786tLso9yxb0gMozbKzXE6TCpMqKIlKQRWAII53QJWxFQQK9\r\n" + 
    			"LoASOpIrlVm6K9MmTFGQMrHSLM923bqReNFytyCDH6kCE1iVRK2JtdI0R3/7/E1SoRhrvI8XitBi\r\n" + 
    			"riOAHnZsjH7CE6dP545DnGvxy58pOzkeFHEk6QcaXEk7S2e+5uCDTR6LyApYc24Z5y2U9qFjGS0y\r\n" + 
    			"F+OlSnmIFsboYxkxXaSIxErr/FVdGVWFbEH6j7NcwUYlgCIqMphBRhg2PqG3XkDJqkV90DjBxwDk\r\n" + 
    			"FU/uI6A46vPL0F/HAwQc48iTCE8krjZuoalQOQ3TzCIVLgGUWU86F6JURatPl7UMBhK2w5lAiKN8\r\n" + 
    			"5S2/ijQEkR5M2dTJWCs8YkzZWLBwWC48w4bpKlgyJpgC6tgh1samyQBK/XgGUNyHzccB9T3zPjpD\r\n" + 
    			"Q9dX3l2h5U+gkWuWSV3SFFdx6SMcZlpPXhXImgggdDkF+nzZEj+WMkICDQM58igxr4QAcVMM9MSi\r\n" + 
    			"JVJAMKAUE88/ue92xUWC6Fj6JZlE+uM9VVuh7JUHqSAUfVpm/la+LbX2OD91JbHJckqED6Bi642I\r\n" + 
    			"SxmfDmTBT4CNPAig/NtbhK754n0y6QJlNlBmmwj3x2QLMY3CwyIQKfjB15LlmBubAwO3YZXLDH3U\r\n" + 
    			"Gg5agNDEASoXpAQx0QSF/7FseUwObkyOqxguZBFBcYwLpESJt4Ys9cGAeksjYFJXSMdievw7NEtG\r\n" + 
    			"SoY+EVA8f7wEyfRTiPyzPXOfaeOs8iLWY3pw4xkSfKeTWihdoimgaV/KEQYGj0/GJAENK9H1kzTh\r\n" + 
    			"zy1iLjyFB2cXItR2G5t1y65f9uBG+10hc/2pwAEU7KZwWkQRoQxLpoQM/QRQ7DoAgoUa14Bo4YlC\r\n" + 
    			"TQ4FogGZacj160ZfhtrkbCGAJpD2QtoShSSshF0S/SZ0WYFuRiQQS8cKzIyT3lJr/Gn28gaZ675O\r\n" + 
    			"Emv2c/SRx3LbX4S6Z9ze54TzRQ4OMBRR0bXnd1AS+NCwSxQi0oboS3OgrmsHxeYQyIC6ZV3rpY8X\r\n" + 
    			"QYkntYbrno4KHoV9H5q3Yp8bHpPhtCsibB/NS08ldsvtxowi/JNdoJbaaTWgM9ADzYml4L78JojJ\r\n" + 
    			"6hRE+QdWHyCL21RExZrEuun71Q0yky3oNKzATj0VvG75o9iULn+sEIlbGB23ajoIeGyp+nvmyDaY\r\n" + 
    			"+PZB6B2bgz1PnwK/38kFc85j2DTl3El22cfK9dDHrZnHPGGHHoSVOFiwtIgAGuGKAjDHnz0XZcry\r\n" + 
    			"7xHgoIZQgbk7t4JsqEx1qWNf+REFy+XdgMwqrsVPZO7rxq7lRxB0Ba13QJrHw/YFCvMihXupIrxN\r\n" + 
    			"4gANL2iYkf7lDPciSOX9owPL06oQcKZvB1ZaBUwe2QKrygTbnQ7qB1hfZpbVkR5YOjRsqoZOXkSN\r\n" + 
    			"0n1Z+tfuGYV2f4PMFwTnH6i5Dx82PswnM23xyj9aT4JMdKrp7ol++3F4LG6z7XaO1aEmTD6wE2aO\r\n" + 
    			"jpoBrX9cgYZ7SzBhyxGevWubsZpioQ2tD6+lvRzAE4/th4njB2Dwrauw+YUL0JxarjPm67iydo/a\r\n" + 
    			"BEoIOLiVT8QjSHjHYiOmDFbEAxLPDojATrU6qC3t6YepYztgVvlOvbz1VcythpQu9sebVDVWrbPZ\r\n" + 
    			"o9tNuffsNDQWOsojFH4KJKctdVolzHxVKe/eHTD49lXY9Oqn0Hd+FuXw6SY4KeN303wZTcPfosTn\r\n" + 
    			"4MnZArU6sMdz2JdWUdjgIhAF72d5XVUKWLhpGCYe3guL+wcIV43JJRj+8zgUKx2Q9n1NIzZZaepm\r\n" + 
    			"ZVsfrG5vmeLQa5+SVApLocsjz52HmQf3Qntrn1Hg7N2jMKesvmdsHrb88RPo/2BazafcD+Iju8+Q\r\n" + 
    			"a5e5YvF9GSslAidneR4o/O2bGaAoU6gaANNq2U/evwtWR/vI5D3j87Dp+XPQ/69JaLSV1osG4YYK\r\n" + 
    			"pQLZHSqQFQIas6vQem8KSBQyhm6jmlbMppfHYfDkOCzcsgWmv3kAVvYNGSCX9w3CxR9+CcqpJdh8\r\n" + 
    			"YhyGX7sEYrUKysweLnjUMocLiaNAFlFqMcKTCINrBf6RiReKftN6fm+Wr/anm3oUqNtg+thOaA83\r\n" + 
    			"QxwQKsfq+3AKhk6MQf+7k1CYl63tBySggxXGs2qeu2eHaev7YAqKNluTmDntk6FhXmwZUoob+OdV\r\n" + 
    			"WLx5M8w8sBeWbtkMsiygvbkPrjx+E0w+sg+GT34Kw3+9pPLmZWq9qYZTxlJOw0DyJEL6ZV/UAIrM\r\n" + 
    			"M5ffmeWvBFrcNwBjP7nVCOFdR7GkApAScuS5c9C8smx+JiCKMriAtXY/mu7y3gFY3dxryoNvXbHe\r\n" + 
    			"UkS+BODlLaLzVD65oTQzoNxA6/QUrCo3Mf3oAVi4YxSq/lJtRpow9fA+uPaNPbD7N6eg9dFMwgun\r\n" + 
    			"j2EEqDkcUh/7JAItaZ9OmVd3mQvg1sq3sz5SthvaHRRE8zoAjTx7HnonVkwQwr+5yGYHgb5zCbdt\r\n" + 
    			"MZZaqNy2pSzeQGisAgcIL60Lhn73pLEWypIVyMXkiuGjUsFu4fBoHKoModPbIHIKjhyn7/si/nGW\r\n" + 
    			"VLIV7awP3O8aEKAyAukrKgSoT+F0XfPCHAy/+AnMH90BnaEe03vxy1th/Mkt0P/OBGx65SK0zs7Y\r\n" + 
    			"99EET+9SWfQ1f8QC0f/2ZSg67vcMqFN6IsbCnJJ/6eAwTH99F8zfuc34bn8V86sw8OYlYwD8zGE9\r\n" + 
    			"9Ov4zx454sc6PBLjNKzuZEqnSFv/cAZGnjkLc/ftUlF7H7S3tYxAWrD5wypqX5yHzc//G/rfvwbF\r\n" + 
    			"csdZSd6Cl5SbWR1tmVRx6NWLbtl3P0QxfPYUsPDFEZh6dL9xLXhceXURhl6+AIOvXIByVSpX1ehO\r\n" + 
    			"t+vlEgOoeVdMVm5LyTpL9we7AEo0vthjlqGysE0qxRp8ddwElOnHDsHKgWHTaWX3AFz6gY7ay7Dp\r\n" + imageStr11+
    			"pQswrEArVqSTDWcBauPwlR0WjOll6L0w75ok7WeGibD6NKgz9+6E6Qf3GD+Lr56xWRh+9pwJqCZL\r\n" + 
    			"8QE1PELn3hTRR0UuO4LL8hvDGbClH89wyfOoQIumI1jfNpuxPkmnV3r5D757DfpPvQFLXxiBWZV/\r\n" + 
    			"Lt6qlmZTR+1emPjOTTB9/27Y99SbDuAYSCsF0vztW0259a8JlyVgxWMZo9I/+cURa+2ep3YFfe9P\r\n" + 
    			"wpByV63T12xA1aAWRWA6zZpT+t0uhK23XBn/uoCmwYkBLd3C4rlzK8lbYPT3Kj9VN/1nZqDvo1Pm\r\n" + 
    			"jGDmkYOwcNd2qAZVqtbTMOkWVZVyCXuHoDNifxWkXUJWIMKH8/0tK5reJms/PayzlMllm/pphYuw\r\n" + 
    			"xrruiSWTk2OMF5twq01f7AcniqmOx56e24Yu2dlji03G+bIS6Id/DQWy8m/Tbdj2uw+g/cwZmLt3\r\n" + 
    			"Fyzct5vt+KxC5+7ebkrNKwvGTwtz1uSVTXdXOFIXcysw+KfzMKQ2EuWc3Sb7TQrOVPhKzPPP6XPh\r\n" + 
    			"45Ec7sMe80jomPf5fcDA1HwZbbazR3o1yyqkRGBDt7D+rbkkYeTFMRhRfleYX265vZJ2CU0VAO+y\r\n" + 
    			"WYJ2Cfr8XhJDwxE7sqa/dj/1hvL5lVv60VLjYYG8Lv5ljbxxTLr7yWQL2iUUa6TMfo0ktLpfqL/F\r\n" + 
    			"2OaqOviRHFJa6xFq77/zV2/C7Nd2w+Drl3y4qZ831CsXZNI8bKlBwM+E/9pGH/klfhLhl8Z6J7xO\r\n" + 
    			"xnK7nLr0y7bZ+t5Li9Dz+48sQKLI9uX01/Wj8M+A/7p+MaBlJuEP4yRaQuEMAiI4JPEO9xIFAoEY\r\n" + 
    			"84O707djrY/9POh/HvxnUjHU4MNJctZGi2FVybR/7slsEIyN60of0u3xZ0r/8+Dfdcye59YFq//G\r\n" + 
    			"TdXNn7s2BH1Zl4ollHyId1U+acVtybS5LAP34SchG4w+ak9/cJJMxoIc2RrmdMnr1lHeSPRDWeIn\r\n" + 
    			"EXHyxCvkFMkmwbuYuh1N7ZnIBqJPNhEeSJI/Sv98IqPfbGCI6rDnsCL674wA6fgNRB/ic8QykvZq\r\n" + 
    			"olx0c+Iye7f+sLGR6aPfRCB/u5av70a1zpV1ixMbhT4qF1lf7AflfLn0/Wq4q6vO5YUbkb7tbL6K\r\n" + 
    			"tAH3rYum3baCSApXFhnONyJ9rAS2/XUOgj1hyFu+zNTl7mm/lKWNRR+TTk9CWJiss3xSJ/Ptdaty\r\n" + 
    			"LXo3On1bZ2tLqifsc/kwQb5I524rzHesO8rfSPRRE335Gd2l87naHOGaIJD2W6vjxqAf3uMAyd7P\r\n" + 
    			"dVqq34ggBZBTpHQarHDfp5sMG4G+x05/l/Pz82GiwaF+uP2OW0E/bokrgLlvdJJftSs4ffqMJStT\r\n" + 
    			"a+cMQoZBnj6GlSoE9YU3Cn0Z+5UTU9cCYLcfvg1uU+Dy3yDkyprK5StX4ec/fRLa7Q5Eu6jJws2q\r\n" + 
    			"STPvtKeL0TJTd0PQj6/Wlvv370+b66wVs6Mmmpr6i++Adiwyv5vxhxe5IELIs7nEDUYfmXtZlk0Y\r\n" + 
    			"uzoHb56+DAvLbVjrOn7sEAy1mua+0Wigg2FqCf5/z66Vt3iZQllku4X37G4k+r7zfwBM+477mIZd\r\n" + 
    			"1QAAAABJRU5ErkJggg2==\r\n";
    	
        boolean generateImage = generateImage(imageStr, "001.jpg");
        System.out.println(generateImage);
    }
}
