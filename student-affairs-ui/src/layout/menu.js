export const menuData = {
    // 管理员菜单
    admin: [
        { path: '/home', label: '首页', icon: 'HomeFilled' },
        { path: '/course', label: '课程管理', icon: 'Operation' },
        { path: '/user', label: '用户管理', icon: 'UserFilled' },
        {
            label: '学生工作',
            icon: 'HelpFilled',
            children: [
                { path: '/student/card', label: '学生证管理'},
                { path: '/student/campus', label: '校园卡管理'},
                { path: '/student/score', label: '成绩管理'},
                { path: '/student/award', label: '奖惩管理'},
                { path: '/student/financial', label: '资助管理'},
                { path: '/student/health', label: '健康管理'},
            ]
        },
        {
            label: '宿舍管理',
            icon: 'Tickets',
            children: [
                { path: '/dormitory', label: '宿舍管理'},
                { path: '/dormitory/clean', label: '卫生检查记录'},
                { path: '/dormitory/discipline', label: '违纪记录'},
                { path: '/dormitory/repair', label: '报修申请'},
                { path: '/dormitory/stay', label: '假期留校申请'},
            ]
        },
        { path: '/statistic', label: '数据分析', icon: 'Magnet' },
    ],
    // 学生菜单
    student: [
        { path: '/home', label: '首页', icon: 'HomeFilled' },
        {
            label: '迎新管理',
            icon: 'BellFilled',
            children: [
                { path: '/student/process', label: '报道流程'},
                { path: '/student/registration', label: '新生报道登记'},
                { path: '/student/sign', label: '新生签到'},
            ]
        },
        {
            label: '学生工作',
            icon: 'HelpFilled',
            children: [
                { path: '/course', label: '学生选课'},
                { path: '/student/card', label: '学生证办理'},
                { path: '/student/campus', label: '校园卡充值'},
                { path: '/student/score', label: '成绩查询'},
                { path: '/student/award', label: '奖惩查询'},
                { path: '/student/financial', label: '资助查询'},
                { path: '/student/health', label: '健康记录'},
            ]
        },
        {
            label: '宿舍管理',
            icon: 'Tickets',
            children: [
                { path: '/student/dormitory', label: '住宿查询'},
                { path: '/dormitory/clean', label: '卫生检查记录'},
                { path: '/dormitory/discipline', label: '违纪记录'},
                { path: '/dormitory/repair/apply', label: '报修申请'},
                { path: '/dormitory/stay/apply', label: '假期留校申请'},
            ]
        },
        {
            label: '毕业管理',
            icon: 'School',
            children: [
                { path: '/graduate/process', label: '毕业流程'},
            ]
        },
    ],
    // 教师菜单
    teacher: [
        { path: '/home', label: '首页', icon: 'HomeFilled' },
        { path: '/course', label: '我的课程', icon: 'Operation' },
        {
            label: '学生工作',
            icon: 'UserFilled',
            children: [
                { path: '/student/score', label: '成绩录入'},
                { path: '/student/award', label: '奖惩发放'},
                { path: '/student/financial', label: '资助发放'},
            ]
        },
        {
            label: '宿舍管理',
            icon: 'OfficeBuilding',
            children: [
                { path: '/dormitory', label: '我负责的宿舍'},
                { path: '/dormitory/clean', label: '卫生检查记录'},
                { path: '/dormitory/discipline', label: '违纪记录'},
                { path: '/dormitory/repair', label: '报修申请处理'},
                { path: '/dormitory/stay', label: '留校申请处理'},
            ]
        },
    ]
}
