# sku-engine-java

电商 SKU 服务端建模与接口示例（基于 Spring Boot）

本项目演示：

- SKU 数据库建模
- 规格与 SKU 关系设计
- specList + skuList 数据结构输出
- 服务端数据组装流程

---

## 🚀 快速开始

### 1. 创建数据库

由于 Spring Boot 不会自动创建数据库实例，请先手动执行：

```sql
CREATE
DATABASE IF NOT EXISTS sku_demo
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;
````

---

### 2. 修改数据库配置

编辑：

```
src/main/resources/application.yml
```

填写你的数据库用户名和密码。

---

### 3. 启动项目

直接运行：

```
SkuEngineJavaApplication
```

项目启动后会自动执行：

* `schema.sql`（建表）
* `data.sql`（初始化数据）

---

### 4. 测试接口

访问：

```
http://localhost:8080/v1/spu/1/detail
```

返回结构示例（简化）：

```json
{
  "specList": [
    {
      "specId": "color",
      "specName": "颜色",
      "values": [
        {
          "id": "black",
          "name": "黑色"
        }
      ]
    }
  ],
  "skuList": [
    {
      "skuId": "1",
      "price": 199.0,
      "stock": 12,
      "specs": {
        "color": "black"
      }
    }
  ],
  "defaultSkuId": "1"
}
```

完整示例请查看：[docs/sku-example.json](docs/sku-example.json)

---

## 📚 相关设计文章

本项目对应服务端实现，建议结合以下文章理解：

* [电商 SKU 系统设计开篇](https://yuncodelab.github.io/system-design/sku/)
* [第一篇：数据结构设计（基于笛卡尔积）](https://yuncodelab.github.io/system-design/sku/sku-part1.html)
* [第二篇：服务端建模与接口设计](https://yuncodelab.github.io/system-design/sku/sku-part2.html)（⭐ 本项目对应）
* [第三篇：Android SKU 选择引擎实现](https://yuncodelab.github.io/system-design/sku/sku-part3.html)

---

## 🧠 设计说明

本项目聚焦“数据建模与接口设计”，刻意简化业务复杂度：

* 未包含 SKU 图片、名称
* 未处理价格区间（如 ¥199~¥299）
* defaultSkuId 为示例数据
* 未涉及库存锁定 / 并发扣减

👉 目标是建立一套清晰、可扩展的 SKU 数据模型

---

## 📂 项目结构（简要）

```
Controller → Service → Repository → DB
```

---

## 🔗 相关项目

* [sku-engine-android](https://github.com/yuncodelab/sku-engine-android)（客户端 SKU 选择引擎）
