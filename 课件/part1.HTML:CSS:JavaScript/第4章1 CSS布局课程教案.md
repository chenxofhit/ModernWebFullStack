# CSS 页面布局课程教案（2小时）

> 面向已学完第4章 HTML+CSS 的学生，系统讲解 CSS 布局的核心概念与实践

---

## 一、课程目标

- **知识目标**：理解标准文档流、浮动、定位、Flex、Grid 的适用场景与核心区别
- **能力目标**：能独立完成导航栏、卡片网格、整页布局等常见布局；能使用大模型提示词辅助生成布局代码
- **思维目标**：建立「先选布局方式，再写 CSS」的布局设计思路

---

## 二、时间分配总览

| 阶段 | 内容 | 时间 |
|------|------|------|
| 1 | 开场与布局思维 | 10 min |
| 2 | 标准文档流与盒模型回顾 | 15 min |
| 3 | 浮动布局（Float） | 25 min |
| 4 | 定位布局（Position） | 25 min |
| 5 | Flex 弹性布局 | 30 min |
| 6 | Grid 网格布局（入门） | 15 min |
| 7 | **大模型演示：用提示词辅助布局** | 15 min |
| 8 | 综合实战与答疑 | 5 min |

---

## 三、详细授课流程

---

### 阶段 1：开场与布局思维（10 min）

**目标**：让学生理解「布局」要解决什么问题，以及如何选择布局方式。

**讲解要点**：

1. **什么是布局？**
   - 把页面上的元素按设计稿放到正确的位置
   - 常见需求：多列、居中、固定顶栏、响应式等

2. **布局方式选择口诀（板书）**：
   ```
   一维排列（横/竖） → Flex
   二维网格（行+列） → Grid
   文字绕图、简单多列 → Float
   精确位置、弹窗、固定栏 → Position
   ```

3. **演示**：打开一个常见网站（如知乎、淘宝），指出：
   - 顶部导航：Flex
   - 商品网格：Grid 或 Flex
   - 侧边栏：Float 或 Grid
   - 返回顶部按钮：Position (fixed)

**互动**：问学生「你们平时做页面时，最常卡在哪种布局上？」（收集痛点，后面针对性讲）

---

### 阶段 2：标准文档流与盒模型回顾（15 min）

**目标**：巩固基础，为理解「脱离文档流」打基础。

**讲解要点**：

1. **标准文档流**
   - 块级元素：独占一行，从上到下
   - 内联元素：从左到右，同一行内
   - 画图示意：几个 div 垂直堆叠

2. **盒模型**
   - content → padding → border → margin
   - `box-sizing: border-box` 的作用（宽高包含 padding、border）

3. **关键概念**：
   - 「脱离文档流」：元素不再占据空间，后面的元素会顶上来
   - Float 和 Position(absolute/fixed) 都会脱离文档流

**演示**：用 DevTools 展示一个 div 的盒模型，并演示 `box-sizing` 切换效果。

---

### 阶段 3：浮动布局（Float）（25 min）

**目标**：理解 float 的用途、副作用（高度塌陷）及清除浮动方法。

**讲解要点**：

1. **Float 的本质**
   - 最初设计目的：文字环绕图片
   - 后来被用来做多列布局（历史原因）

2. **核心属性**
   - `float: left` / `float: right`
   - `clear: left` / `clear: right` / `clear: both`

3. **经典问题：高度塌陷**
   - 父元素只包含浮动子元素时，高度变为 0
   - 原因：浮动元素脱离文档流，父元素「看不到」它们的高度

4. **清除浮动的三种方法**（板书）：
   ```css
   /* 方法1：overflow */
   .parent { overflow: hidden; }

   /* 方法2：clearfix（推荐） */
   .clearfix::after {
     content: "";
     display: block;
     clear: both;
   }

   /* 方法3：空 div */
   <div style="clear: both;"></div>
   ```

**演示**：使用 `float-demo.html`，重点演示：
- 15-9：第一个盒子左浮动，文字环绕
- 15-11：clear: left 的效果
- 11：高度塌陷 + clearfix 解决

**小结**：Float 适合文字绕图、简单多列；现代布局优先考虑 Flex/Grid。

---

### 阶段 4：定位布局（Position）（25 min）

**目标**：掌握 5 种定位方式及典型应用场景。

**讲解要点**：

1. **5 种定位方式对比表**（板书或 PPT）：

   | 值 | 参考系 | 是否脱离文档流 | 典型用途 |
   |----|--------|----------------|----------|
   | static | 无 | 否 | 默认 |
   | relative | 自身原位置 | 否 | 微调、为 absolute 提供参考 |
   | absolute | 最近已定位祖先 | 是 | 弹窗、徽章、下拉菜单 |
   | fixed | 视口 | 是 | 固定导航、返回顶部 |
   | sticky | 滚动到阈值前相对，之后固定 | 混合 | 吸顶导航 |

2. **重点强调**
   - `position: relative` 不脱离文档流，常用来给 `absolute` 子元素当「锚点」
   - `absolute` 的「最近已定位祖先」：往上找第一个 `position` 不是 `static` 的祖先

3. **典型代码模式**：
   ```html
   <div class="parent" style="position: relative;">
     <div class="child" style="position: absolute; top: 0; right: 0;">
       右上角徽章
     </div>
   </div>
   ```

**演示**：使用 `position-demo.html`，重点演示：
- 相对定位：微调
- 绝对定位：相对于父容器
- 固定定位：滚动页面观察
- 粘性定位：滚动观察吸顶效果

**小结**：Position 适合「精确位置」「悬浮」「固定」类需求，不适合做主布局。

---

### 阶段 5：Flex 弹性布局（30 min）★ 重点

**目标**：熟练使用 Flex 完成导航栏、居中、卡片等常见布局。

**讲解要点**：

1. **Flex 的两层概念**
   - **容器**：`display: flex` 的元素
   - **项目**：容器的直接子元素

2. **主轴与交叉轴**（画图）
   - `flex-direction: row`（默认）：主轴水平，交叉轴垂直
   - `flex-direction: column`：主轴垂直，交叉轴水平
   - 主轴方向决定「排列方向」，交叉轴决定「对齐方向」

3. **容器常用属性**（板书）：
   ```
   flex-direction: row | column | row-reverse | column-reverse
   justify-content: flex-start | center | flex-end | space-between | space-around | space-evenly
   align-items: flex-start | center | flex-end | stretch
   flex-wrap: nowrap | wrap
   ```

4. **项目常用属性**：
   ```
   flex: 1          → 占满剩余空间
   flex: 0 0 200px  → 固定 200px，不伸缩
   align-self       → 覆盖容器的 align-items
   ```

5. **三个必会场景**：
   - **水平居中**：`display: flex; justify-content: center; align-items: center;`
   - **导航栏**：`justify-content: space-between` + 左右两组
   - **等分/响应式卡片**：`flex-wrap: wrap` + `flex: 0 0 calc(33.333% - 20px)`

**演示**：使用 `flex-tutorial.html`，重点演示：
- 2：flex-direction 四种方向
- 3：justify-content 六种对齐（可点按钮）
- 4：align-items 五种对齐
- 5：flex-wrap 换行
- 8：导航栏、卡片、表单三个实际示例

**课堂练习**（5 min）：让学生用 Flex 写一个「三列等宽」布局，并加上 `flex-wrap` 实现窄屏时变一列。

---

### 阶段 6：Grid 网格布局（入门）（15 min）

**目标**：了解 Grid 的二维特性，能写出简单的网格布局。

**讲解要点**：

1. **Flex vs Grid**
   - Flex：一维（行或列）
   - Grid：二维（行 + 列同时控制）

2. **基本用法**：
   ```css
   .grid {
     display: grid;
     grid-template-columns: 1fr 1fr 1fr;  /* 三列等宽 */
     grid-template-rows: 100px auto;
     gap: 20px;
   }
   ```

3. **常用单位**
   - `fr`：比例单位，类似 flex-grow
   - `repeat(3, 1fr)`：等价于 `1fr 1fr 1fr`

4. **典型场景**：整页布局（header / sidebar / main / footer）

**演示**：使用 `grid-demo.html`，依次展示：三列等宽、repeat、不等宽、整页布局（grid-template-areas）。

**小结**：Grid 适合整页、复杂网格；简单一维布局用 Flex 更轻量。

---

### 阶段 7：大模型演示——用提示词辅助布局（15 min）

**目标**：让学生学会用大模型（如 ChatGPT、Cursor、通义千问等）通过提示词快速生成布局代码，并理解如何写出有效的布局提示词。

**讲解要点**：

1. **为什么用大模型辅助布局？**
   - 快速产出可用的 HTML/CSS 代码
   - 学习标准写法与最佳实践
   - 减少查文档、试错的时间

2. **布局提示词三要素**（板书）：
   ```
   ① 说清布局方式：Flex / Grid / Float / Position
   ② 描述结构：有哪些区域、如何排列
   ③ 补充约束：响应式、尺寸、对齐方式
   ```

3. **示例提示词**（课堂演示用）：
   - **简单**：「用 Flex 写一个顶部导航栏，左边 logo，右边三个链接，两端对齐」
   - **中等**：「用 Grid 做一个三栏布局：左侧边栏 200px，主内容自适应，右侧栏 150px，顶部 header 通栏」
   - **带响应式**：「用 Flex 实现三列卡片布局，桌面端三列，平板两列，手机一列，带 gap 间距」

4. **提示词技巧**：
   - 明确技术栈：如「纯 HTML+CSS」「不用 JavaScript」
   - 指定布局方式：避免模型选错（如用 Float 做导航）
   - 分步追问：先要结构，再要样式，最后要响应式

5. **现场演示**（建议用 Cursor / ChatGPT）：
   - 输入：「用 Flex 写一个导航栏，左边「我的网站」，右边「首页 关于 联系」，深色背景白字」
   - 展示生成代码，在浏览器中打开验证
   - 追问：「加上移动端时变成竖排」观察模型如何补充媒体查询

**小结**：大模型是辅助工具，理解布局原理才能判断生成代码是否正确、如何修改。

---

### 阶段 8：综合实战与答疑（5 min）

**目标**：串联知识，解答疑问。

1. **布局选择决策树**（板书，快速回顾）：
   ```
   需要精确位置/弹窗/固定栏？ → Position
   需要文字绕图？ → Float
   是一维排列（横或竖）？ → Flex
   是二维网格/整页布局？ → Grid
   ```

2. **综合示例**：用 1 分钟演示一个简单页面：
   - 顶部：Flex 导航
   - 主体：Grid 或 Flex 卡片
   - 右下角：Position fixed 返回顶部

3. **答疑**：针对学生提问，结合 `CSS布局总结.md` 中的对比表做补充说明。

---

## 四、教学资源清单

| 资源 | 路径 | 用途 |
|------|------|------|
| CSS布局总结 | `CSS布局总结.md` | 课后复习、对比查阅 |
| Float 演示 | `float-demo.html` | 阶段 3 课堂演示 |
| Position 演示 | `position-demo.html` | 阶段 4 课堂演示 |
| Flex 教程 | `flex-tutorial.html` | 阶段 5 课堂演示 + 学生自学 |
| Grid 演示 | `grid-demo.html` | 阶段 6 课堂演示 |

---

## 五、课后作业建议

1. **基础**：用 Flex 实现一个响应式导航栏（桌面端横排，移动端可考虑竖排）
2. **进阶**：用 Flex 或 Grid 实现一个「三列卡片」布局，窄屏时变为一列
3. **综合**：模仿一个简单网站首页的布局（header + 多列内容 + footer）
4. **大模型练习**：用 ChatGPT/Cursor 等，通过提示词生成一个「左侧固定 200px 侧边栏 + 右侧自适应主内容」的布局，并尝试追问「加上顶部 header」观察模型如何修改

---

## 六、常见学生问题预案

| 问题 | 回答要点 |
|------|----------|
| Flex 和 Float 什么时候用哪个？ | 现代布局优先 Flex；Float 主要用于文字绕图 |
| 为什么我的 Flex 不生效？ | 检查是否写在「父元素」上，子元素是否直接子节点 |
| Grid 和 Flex 能一起用吗？ | 可以，外层 Grid 分区域，内层 Flex 排内容 |
| 高度塌陷怎么记？ | 父元素里全是 float 子元素 → 父高度为 0 → 用 clearfix |
| `position: absolute` 相对于谁？ | 最近 `position` 非 `static` 的祖先，没有则相对于视口 |
| 大模型生成的布局代码不对怎么办？ | 在提示词中明确指定 Flex/Grid，或分步描述；理解原理后自己微调 |

---

## 七、板书/PPT 建议结构

1. **开场**：布局选择口诀
2. **Float**：高度塌陷示意图 + clearfix 代码
3. **Position**：5 种方式对比表
4. **Flex**：主轴/交叉轴示意图 + 容器/项目属性表
5. **Grid**：基本语法 + 与 Flex 对比
6. **大模型**：布局提示词三要素 + 示例提示词
7. **结尾**：布局选择决策树

---

*教案版本：v1.0 | 适用：2 小时 CSS 布局专题课*
