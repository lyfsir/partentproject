package com.lyf.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author lyf
 * @date 2020/7/16-22:13
 */

/**
 *      "id": {
 *         "type": "long"
 *       },
 *       "title": {
 *         "type": "text",
 *         "analyzer": "ik_smart"
 *       },
 *       "username": {
 *         "type": "keyword"
 *       },
 *      "category":{
 *         "type": "nested",
 *         "properties": {
 *             "cId":{
 *                 "type": "keyword"
 *               },
 *             "cName":{
 *                 "type": "keyword"
 *               }
 *         }
 *       },
 *       "imgurl":{
 *         "type": "keyword",
 *         "index": false,
 *         "doc_values": false
 *       },
 *       "hostScore":{
 *         "type": "long"
 *       },
 *       "attr":{
 *         "type": "nested",
 *         "properties": {
 *           "attrIid":{
 *             "type": "long"
 *           },
 *           "attrName":{
 *             "type": "keyword"
 *           },
 *           "attrValue":{
 *             "type": "keyword",
 *             "index": false,
 *             "doc_values": false
 *           }
 *         }
 *       }
 *     }
 */
@Data
public class FoodInfosModel {
    private Long id;
    private String title;
    private String username;

    private List<Category> category;


    private String imgurl;
    /**
     * 专题id
     */
    private Long sId;
    private List<Attr> attr;
    //当前时间
    private String createTime;

    @Data
    public static class Attr{
        private Long attrIid;
        private String attrName;
        private String attrValue;
    }

    @Data
    public static class Category{
        private Long cId;
        private String cName;
    }

}
