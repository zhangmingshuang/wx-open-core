package com.magneton.we.api.open.usermanagement;

import com.magneton.we.api.open.usermanagement.pojo.Tag;
import com.magneton.we.api.open.usermanagement.pojo.TagFans;

import java.util.List;

/**
 * 用户管理-用户标签管理
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/User_Management/User_Tag_Management.html
 * <p>
 * 开发者可以使用用户标签管理的相关接口，
 * 实现对公众号的标签进行创建、查询、修改、删除等操作，
 * 也可以对用户进行打标签、取消标签等操作。
 */
public interface UserTag {
    /**
     * 创建标签
     * <p>
     * 错误码	说明
     * -1	系统繁忙
     * 45157	标签名非法，请注意不能和其他标签重名
     * 45158	标签名长度超过30个字节
     * 45056	创建的标签数过多，请注意不能超过100个
     *
     * @param name
     * @return
     */
    Tag create(String name);

    /**
     * 获取公众号已创建的标签
     *
     * @return 已创建的标签
     */
    List<Tag> get();

    /**
     * 编辑标签
     * <p>
     * 错误码	说明
     * -1	系统繁忙
     * 45157	标签名非法，请注意不能和其他标签重名
     * 45158	标签名长度超过30个字节
     * 45058	不能修改0/1/2这三个系统默认保留的标签
     *
     * @param id   标签Id,创建微信标签时微信返回
     * @param name
     * @return
     */
    boolean update(long id, String name);

    /**
     * 删除标签
     * <p>
     * 请注意，当某个标签下的粉丝超过10w时，后台不可直接删除标签。
     * 此时，开发者可以对该标签下的openid列表，先进行取消标签的操作，
     * 直到粉丝数不超过10w后，才可直接删除该标签。
     * <p>
     * 错误码	说明
     * -1	系统繁忙
     * 45058	不能修改0/1/2这三个系统默认保留的标签
     * 45057	该标签下粉丝数超过10w，不允许直接删除
     *
     * @param id 标签Id,创建微信标签时微信返回
     * @return
     */
    boolean delete(long id);

    /**
     * 获取标签下粉丝列表
     * <p>
     * 错误码	说明
     * -1	系统繁忙
     * 40003	传入非法的openid
     * 45159	非法的tag_id
     *
     * @param id         标签Id,创建微信标签时微信返回
     * @param nextOpenId 第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    TagFans getFans(long id, String nextOpenId);

    /**
     * 批量为用户打标签
     * <p>
     * 错误码	说明
     * -1	系统繁忙
     * 40032	每次传入的openid列表个数不能超过50个
     * 45159	非法的标签
     * 45059	有粉丝身上的标签数已经超过限制，即超过20个
     * 40003	传入非法的openid
     * 49003	传入的openid不属于此AppID
     *
     * @param id      标签Id,创建微信标签时微信返回
     * @param openIds 粉丝列表
     * @return
     */
    boolean batchAgging(long id, String[] openIds);

    /**
     * 批量为用户取消打标
     * <p>
     * 错误码	说明
     * -1	系统繁忙
     * 40032	每次传入的openid列表个数不能超过50个
     * 45159	非法的标签
     * 40003	传入非法的openid
     * 49003	传入的openid不属于此AppID
     *
     * @param id
     * @param openIds
     * @return
     */
    boolean batchUntagging(long id, String[] openIds);

    /**
     * 获取用户身上的标签列表
     * <p>
     * 错误码	说明
     * -1	系统繁忙
     * 40003	传入非法的openid
     * 49003	传入的openid不属于此AppID
     *
     * @param openId
     * @return
     */
    List<String> getIdList(String openId);
}
