//app.js
App({

  //*小程序启动之后触发
  onLaunch: function (obj) {
    // 展示本地存储能力
    //var logs = wx.getStorageSync('logs') || []
    //logs.unshift(Date.now())
    //wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.request({
            url: this.globalData.basicUrl +  '/wx/user/wx4a9978234d406a66/login',
            header: {
              'content-type': 'application/json' // 默认值
            },
            data: { code: res.code },
            success: function (res) {
              if (res.statusCode == 200) {
                wx.setStorageSync('sessionKey', res.data.sessionKey)
                wx.setStorageSync('openId', res.data.openid)
              }
            }

          })
        }

      }
    })

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {  
              wx.request({
                url: this.globalData.basicUrl + '/wx/user/wx4a9978234d406a66/info',
                data: {
                  sessionKey: wx.getStorageSync('sessionKey'),
                  signature: res.signature,
                  rawData: res.rawData,
                  encryptedData: res.encryptedData,
                  iv: res.iv
                },
                header: {
                  'content-type': 'application/json' // 默认值
                },
                success: res => {
                  if (res.statusCode == 200) {
                    console.log(res)
                    console.log(res.data)
                    this.globalData.userInfo = res.data
                  }
                  
                }
              })
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo
              //console.log(this.globalData.userInfo)
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
               this.userInfoReadyCallback(res)  
              }
            }
          })
        }
      } 
    })
  }, //end onLaunch
  globalData: {
    userInfo: null,
    basicUrl: 'https://whalenchan.tunnel.qydev.com',
    appId: 'wx4a9978234d406a66'
  },
  onShow: function(params) {
  },
  showGlobalToast: function() {
    wx.showToast({
      title: '数据加载中',
      icon: 'loading',
      duration: 3000
    });
  },
  hideGlobalToast: function() {
    wx.hideToast()   
  }           
})                        