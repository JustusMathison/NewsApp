package com.example.newsapp;

import java.util.ArrayList;
import java.util.List;

public class NewsData {
    public static List<News> getInitialNewsData() { // 方法名可以改一下以示区分
        List<News> newsList = new ArrayList<>();

        newsList.add(new News("《哪吒 2》热度降低",
                "《哪吒 2》热度降低后，年初被「盘活」的影院再次面临闭店，背后有哪些深层原因？如何破局？",
                "近日，“清明档3.7亿收官”的话题引发关注。据灯塔专业版数据，截至4月6日21时，2025年清明档票房3.77亿元，不到去年8.43亿档期票房的一半，观影人次923.6万，同样不到去年的一半。\n" +
                        "\n" +
                        "这与春节档电影市场火热的现场形成鲜明的对比。猫眼研究院发布的《2025春节档电影数据洞察》显示，2025年春节档总票房为95.10亿元，同比大幅增长18.6%，观影人次达1.87亿，同比增长14.7%，双双刷新春节档影史纪录。\n" +
                        "\n" +
                        "这与此次春节档一众影片的优秀表现密不可分。其中，《哪吒之魔童闹海》（以下简称：《哪吒2》）档期票房成绩实现断层领先，刷新了中国内地影史春节档电影的单片票房纪录。“《哪吒2》拯救县城影院”“靠《哪吒2》影院7天赚了半年的钱”等词条冲上热搜。\n" +
                        "\n" +
                        "然而，《哪吒2》热度降低后，年初那些被哪吒“盘活”的影院开始重新焦虑。随着影市淡季的到来，影院“闭店潮”再次出现。\n" +
                        "\n" +
                        "潮新闻此前报道，据不完全统计，今年2月末到3月初的半个月内，国内至少有15家影院公开发布了闭店通知。其中影响最大的是3月1日，UME影城（北京华星店）宣布闭店。在此之前，这家影业已经经营22年，是北京第一家引入IMAX的影院。此外CGV长沙麓山店、上海卢米埃大融城影城、佛山兆阳保利国际影城等，也都选择了关门。",
                "2025-04-13"));

        newsList.add(new News( "缅甸发生地震",
                "缅甸发生5.4级地震，震源深度20千米",
                "中国地震台网正式测定：04月13日10时24分在缅甸（北纬21.00度，东经95.95度）发生5.4级地震，震源深度20千米。",
                "2025-04-15"));

        newsList.add(new News("强硬反制，中方对美所有进口商品加征关税税率提高至84%",
                "美方升级对华关税的做法是错上加错，严重侵犯中国正当权益。" ,
                "据央视新闻周三发布消息，国务院关税税则委员会今天发布公告，2025年4月8日，美国政府宣布对中国输美商品征收“对等关税”的税率由34%提高至84%。美方升级对华关税的做法是错上加错，严重侵犯中国正当权益，严重损害以规则为基础的多边贸易体制。\n" +
                        "\n" +
                        "根据《中华人民共和国关税法》、《中华人民共和国海关法》、《中华人民共和国对外贸易法》等法律法规和国际法基本原则，经国务院批准，自2025年4月10日12时01分起，调整对原产于美国的进口商品加征关税措施。",
                "2025-04-14"));

        newsList.add(new News("中纪委周末“打虎”，山西省省长金湘军任上被查",
                "金湘军本周还出席了两场公开活动。",
                "据中央纪委国家监委网站4月12日消息，山西省委副书记、省长金湘军涉嫌严重违纪违法，目前正接受中央纪委国家监委纪律审查和监察调查。\n" +
                        "\n" +
                        "公开简历显示，金湘军，男，汉族，1964年7月生，研究生，管理学博士，中共党员。现任二十届中央委员。\n" +
                        "\n" +
                        "金湘军历任海南航空旅业开发股份有限公司总经济师兼经营部总经理，海南省社会保障局基金管理处处长，广西壮族自治区劳动厅副厅长、劳动和社会保障厅副厅长，玉林市副市长、市委常委，玉林市市长，玉林市委书记，防城港市委书记等职。",
                "2025-04-12"));



        newsList.add(new News("原神原神原神小米SU7湛江再发交通事故，公司回应起火原因",
                "小米汽车表示，相关火情是碰撞后电动二轮车锂电池严重挤压和变形起火，再引燃事故车辆。\n" ,
                "据珠江新闻报道，近日有网友发帖称，广东湛江市有一辆小米SU7撞倒两人后逃逸，汽车随后起火焚毁。\n" +
                        "\n" +
                        "对此，小米汽车4月11日就该事故发布情况说明，称在4月5日获悉，当日凌晨，一辆小米SU7标准版在湛江市徐闻县曲界镇376省道，在驾驶员（经查非车主本人）手动驾驶状态行驶过程中，与一辆电动二轮车发生碰撞。事故发生后，肇事司机弃车逃逸。据警方最新通报，肇事司机目前已被执行逮捕。\n" +
                        "\n" +
                        "关于事故中汽车为什么会被引燃，小米汽车表示，目前现场勘查已完成，最终结论以相关部门报告为准。据初步了解，相关火情是碰撞后电动二轮车锂电池严重挤压和变形起火，再引燃事故车辆。\n" +
                        "\n" +
                        "同日，小米还发布了另外一份声明，强调网上流传的所谓“国家新能源汽车事故鉴定中心4月7日公布的初步分析”内容完全失实，纯属编造，该机构根本并不存在，公司已依法向公安机关报案。\n" +
                        "\n" +
                        "此时距离安徽小米SU7交通事故仅过去7天。3月29日，一辆小米SU7在G0321德上高速池祁段撞中分带新泽西护栏后发生燃烧，造成三人死亡。\n" +
                        "\n" +
                        "4月1日，“小米公司发言人” 微博官方账号发布了小米SU7事故的详细说明。当天深夜，小米公司及创始人雷军也作出官方回应，针对家属和外界的种种回应给出了相应解释，并称“小米不会回避，将持续配合警方，跟进进展，尽力回应家属和社会关切。”\n" +
                        "\n" +
                        "目前，安徽相关事故仍在调查中，此前遇难者母亲曾发文质疑小米未主动联系，后清空相关微博。4月2日，遇难者父亲与交警、小米汽车相关人员就事故事宜进行了商谈。\n" +
                        "\n" +
                        "受上述事件影响，小米股价和市值出现大幅波动。4月2日，小米集团早间开盘后继续走低，盘中一度跌逾4%。午后跌幅再度扩大，收盘跌4.19%。按照当时市值计算，小米集团总市值两天缩水超1200亿港元。\n" +
                        "\n" +
                        "而就在外界等待安徽SU7事故最终调查结果出炉时，小米又被一车主在行驶途中酣睡及多家保险公司拒给小米SU7续保谣言再次推上风口浪尖。\n" +
                        "\n" +
                        "4月2日，网上曝出的视频显示，一辆小米汽车正常行驶过程中，车主双手离开方向盘，躺在驾驶座位上呼呼大睡。同行友人见状，连喊三遍 “减速”，提醒避让该小米汽车。此视频一经传播，迅速攀升至热搜榜首，引发网友对车辆智能驾驶安全的担忧。\n" +
                        "\n" +
                        "对此，小米汽车客服工作人员向界面新闻表示，使用智驾功能手必须要握住方向盘，如果双手离开，系统通常会有四次预警，且声音逐渐加大，并提醒司机立即接管方向盘。\n" +
                        "\n" +
                        "而针对社交媒体上多位网友发文称小米SU7续保遭遇难题，4月7日晚，小米公司发言人在其官方微博上发文称，目前SU7车型的投保服务平稳正常，网传信息严重失实。\n" +
                        "\n" +
                        "尽管小米针对多个事件都有积极回应，但其汽车销量短时受到了影响。最新上险量数据显示，3月31日-4月6日，在“3·29小米SU7爆燃事故”后的完整首周，小米汽车销量为0.5万辆，排名下降3位至21名，创下了近两个月以来小米汽车最低周销量。过去两个月中，小米汽车周销量从未低于0.6万辆，最高时在3月24日-3月30日达到了0.78万辆。\n" +
                        "\n" +
                        "小米原本计划通过现有产线将2025年交付目标从30万辆提升至35万辆，并计划北京二期工厂年内投产，年产能规划15万辆。但连续的事故可能会使部分潜在消费者持观望态度，给小米完成交付目标增添了不确定性。",
                "2025-04-11"));



        return newsList;
    }
}