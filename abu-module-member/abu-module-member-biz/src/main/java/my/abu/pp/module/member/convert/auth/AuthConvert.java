package my.abu.pp.module.member.convert.auth;

import my.abu.pp.module.member.controller.app.auth.vo.*;
import my.abu.pp.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import my.abu.pp.module.member.controller.app.user.vo.AppMemberUserResetPasswordReqVO;
import my.abu.pp.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import my.abu.pp.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import my.abu.pp.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import my.abu.pp.module.system.api.sms.dto.code.SmsCodeValidateReqDTO;
import my.abu.pp.module.system.api.social.dto.SocialUserBindReqDTO;
import my.abu.pp.module.system.api.social.dto.SocialUserUnbindReqDTO;
import my.abu.pp.module.system.api.social.dto.SocialWxJsapiSignatureRespDTO;
import my.abu.pp.module.system.enums.sms.SmsSceneEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialLoginReqVO reqVO);
    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

    SmsCodeSendReqDTO convert(AppAuthSmsSendReqVO reqVO);
    SmsCodeUseReqDTO convert(AppMemberUserResetPasswordReqVO reqVO, SmsSceneEnum scene, String usedIp);
    SmsCodeUseReqDTO convert(AppAuthSmsLoginReqVO reqVO, Integer scene, String usedIp);

    AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean, String openid);

    SmsCodeValidateReqDTO convert(AppAuthSmsValidateReqVO bean);

    SocialWxJsapiSignatureRespDTO convert(SocialWxJsapiSignatureRespDTO bean);

}
