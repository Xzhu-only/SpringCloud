<template>
  <el-card style="margin-top: -20px;margin-bottom: 10px">
    <h2 style="text-align: center">课程成绩数据统计</h2>
    <div id="echarts1" :style="{ width: '100%', height: '500px' }"></div>
  </el-card>

  <el-card style="margin-bottom: 10px">
    <h2 style="text-align: center">七天内宿舍违纪记录统计</h2>
    <div id="echarts4" :style="{ width: '100%', height: '500px' }"></div>
  </el-card>

  <el-row :gutter="10">
    <el-col :span="12">
      <el-card>
        <h2 style="text-align: center">课程选课统计</h2>
        <div id="echarts2" :style="{ width: '100%', height: '400px' }"></div>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card>
        <h2 style="text-align: center">课程成绩分组统计</h2>
        <div id="echarts3" :style="{ width: '100%', height: '400px' }"></div>
      </el-card>
    </el-col>
  </el-row>

  <el-row :gutter="10" style="margin-top: 10px">
    <el-col :span="12">
      <el-card>
        <h2 style="text-align: center">奖惩比例统计</h2>
        <div id="echarts5" :style="{ width: '100%', height: '400px' }"></div>
      </el-card>
    </el-col>
    <el-col :span="12">
      <el-card>
        <h2 style="text-align: center">学生选课偏好</h2>
        <div id="echarts6" :style="{ width: '100%', height: '400px' }"></div>
      </el-card>
    </el-col>
  </el-row>
</template>

<script setup>
import * as echarts from "echarts";
import {useUserStore} from "@/stores/user.js";
import {onMounted} from "vue";
import app from "@/App.vue";
import {
  getEcharts1Api,
  getEcharts2Api,
  getEcharts3Api,
  getEcharts4Api,
  getEcharts5Api,
  getEcharts6Api
} from "@/api/statistic.js";

const userStore = useUserStore()
let echart = echarts;
const getEcharts1 = async () => {
  const posList = [
    'left',
    'right',
    'top',
    'bottom',
    'inside',
    'insideTop',
    'insideLeft',
    'insideRight',
    'insideBottom',
    'insideTopLeft',
    'insideTopRight',
    'insideBottomLeft',
    'insideBottomRight'
  ];
  app.configParameters = {
    rotate: {
      min: -90,
      max: 90
    },
    align: {
      options: {
        left: 'left',
        center: 'center',
        right: 'right'
      }
    },
    verticalAlign: {
      options: {
        top: 'top',
        middle: 'middle',
        bottom: 'bottom'
      }
    },
    position: {
      options: posList.reduce(function (map, pos) {
        map[pos] = pos;
        return map;
      }, {})
    },
    distance: {
      min: 0,
      max: 100
    }
  };
  app.config = {
    rotate: 90,
    align: 'left',
    verticalAlign: 'middle',
    position: 'insideBottom',
    distance: 15,
    onChange: function () {
      const labelOption = {
        rotate: app.config.rotate,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,
        position: app.config.position,
        distance: app.config.distance
      };
      myChart.setOption({
        series: [
          {
            label: labelOption
          },
          {
            label: labelOption
          },
          {
            label: labelOption
          },
          {
            label: labelOption
          }
        ]
      });
    }
  };
  const labelOption = {
    show: true,
    position: app.config.position,
    distance: app.config.distance,
    align: app.config.align,
    verticalAlign: app.config.verticalAlign,
    rotate: app.config.rotate,
    formatter: '{c}  {name|{a}}',
    fontSize: 16,
    rich: {
      name: {}
    }
  };

  const res = await getEcharts1Api()
  if (res.code === 200) {
    // 从响应中提取数据
    const data = res.data;

    // 提取课程名称（xAxis 数据）
    const courseNames = Object.keys(data);

    // 提取每门课程的平均成绩、最大成绩、最小成绩（series 数据）
    const averageScores = [];
    const maxScores = [];
    const minScores = [];

    courseNames.forEach(course => {
      const scores = data[course][0]; // 获取每门课程的成绩数组
      averageScores.push(scores[0]);
      maxScores.push(scores[1]);
      minScores.push(scores[2]);
    });

    // 初始化图表
    var chart = echart.init(document.getElementById("echarts1"));
    chart.setOption({
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow'
        }
      },
      legend: {
        data: ['平均成绩', '最大成绩', '最小成绩']
      },
      toolbox: {
        show: true,
        orient: 'vertical',
        left: 'right',
        top: 'center',
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: false },
          magicType: { show: true, type: ['line', 'bar', 'stack'] },
          restore: { show: true },
          saveAsImage: { show: true }
        }
      },
      xAxis: [
        {
          type: 'category',
          axisTick: { show: false },
          data: courseNames // 将课程名称作为 xAxis 数据
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: '平均成绩',
          type: 'bar',
          barGap: 0,
          label: {
            show: true,
            position: 'insideTop'
          },
          emphasis: {
            focus: 'series'
          },
          data: averageScores // 平均成绩数据
        },
        {
          name: '最大成绩',
          type: 'bar',
          label: {
            show: true,
            position: 'insideTop'
          },
          emphasis: {
            focus: 'series'
          },
          data: maxScores // 最大成绩数据
        },
        {
          name: '最小成绩',
          type: 'bar',
          label: {
            show: true,
            position: 'insideTop'
          },
          emphasis: {
            focus: 'series'
          },
          data: minScores // 最小成绩数据
        }
      ]
    });
  }
}

const getEcharts2 = async () => {
  const res = await getEcharts2Api()
  if (res.code === 200) {
    var chart = echart.init(document.getElementById("echarts2"));
    chart.setOption({
      legend: {
        top: 'bottom'
      },
      toolbox: {
        show: true,
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: false },
          restore: { show: true },
          saveAsImage: { show: true }
        }
      },
      series: [
        {
          name: '数量',
          type: 'pie',
          radius: [50, 140],
          center: ['50%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 8
          },
          data: res.data,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    })
  }
}

const getEcharts3 = async () => {
  const res = await getEcharts3Api()
  if (res.code === 200) {
    var chart = echart.init(document.getElementById("echarts3"));
    chart.setOption({
      title: {
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '数量',
          type: 'pie',
          radius: '50%',
          data: res.data,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    })
  }
}

const getEcharts4 = async () => {
  const res = await getEcharts4Api();
  if (res.code === 200) {
    const data = res.data;

    // 处理日期和每栋宿舍的违纪记录
    const xAxisData = Object.keys(data).reduce((acc, dormName) => {
      const dates = Object.keys(data[dormName]);
      dates.forEach(date => {
        if (!acc.includes(date)) {
          acc.push(date);
        }
      });
      return acc;
    }, []);

    // 将日期进行升序排序
    xAxisData.sort((a, b) => new Date(a) - new Date(b));

    const seriesData = Object.keys(data).map(dormName => {
      const dataValues = xAxisData.map(date => data[dormName][date] || 0);
      return {
        name: dormName,
        type: 'line',
        stack: '违纪记录',
        data: dataValues
      };
    });

    var chart = echart.init(document.getElementById("echarts4"));
    chart.setOption({
      title: {
        text: '宿舍违纪记录统计'
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: Object.keys(data)
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      xAxis: {
        type: 'category',
        boundaryGap: false,
        data: xAxisData
      },
      yAxis: {
        type: 'value'
      },
      series: seriesData
    });
  }
}

const getEcharts5 = async () => {
  const res = await getEcharts5Api()
  if (res.code === 200) {
    var chart = echart.init(document.getElementById("echarts5"));
    chart.setOption({
      title: {
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '数量',
          type: 'pie',
          radius: '50%',
          data: res.data,
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    })
  }
}

const getEcharts6 = async () => {
  const res = await getEcharts6Api()
  const res1 = await getEcharts2Api()
  console.log(Object.values(res1.data))
  if (res.code === 200) {
    // 提取 res1.data 中每个对象的 value 属性，并组成一个数组
    const valueArray = Object.values(res1.data).map(item => item.value);

    var chart = echart.init(document.getElementById("echarts6"));
    chart.setOption({
      legend: {
        data: ['学生用户']
      },
      radar: {
        // shape: 'circle',
        indicator: res.data
      },
      series: [
        {
          name: 'Budget vs spending',
          type: 'radar',
          data: [
            {
              value: valueArray,
              name: '学生用户'
            }
          ]
        }
      ]
    })
  }
}

onMounted(() => {
  getEcharts1()
  getEcharts2()
  getEcharts3()
  getEcharts4()
  getEcharts5()
  getEcharts6()
})
</script>

<style scoped>

</style>