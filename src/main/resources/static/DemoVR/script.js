(function(){
    var script = {
 "defaultVRPointer": "laser",
 "scrollBarOpacity": 0.5,
 "layout": "absolute",
 "id": "rootPlayer",
 "children": [
  "this.MainViewer",
  "this.IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA"
 ],
 "scrollBarVisible": "rollOver",
 "start": "this.init()",
 "overflow": "visible",
 "width": "100%",
 "scrollBarMargin": 2,
 "class": "Player",
 "borderRadius": 0,
 "verticalAlign": "top",
 "paddingLeft": 0,
 "paddingRight": 0,
 "propagateClick": false,
 "minHeight": 20,
 "scrollBarWidth": 10,
 "desktopMipmappingEnabled": false,
 "mobileMipmappingEnabled": false,
 "vrPolyfillScale": 0.5,
 "minWidth": 20,
 "definitions": [{
 "frames": [
  {
   "front": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/f/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/f/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/f/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "top": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/u/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/u/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/u/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "right": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/r/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/r/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/r/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "back": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/b/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/b/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/b/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "thumbnailUrl": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_t.jpg",
   "bottom": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/d/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/d/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/d/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "class": "CubicPanoramaFrame",
   "left": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/l/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/l/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0/l/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   }
  }
 ],
 "label": "PanaramaC_V2City_Grass_WaterMark",
 "id": "panorama_17FF3516_1812_A6D3_41A4_6B9087789C50",
 "overlays": [
  "this.overlay_276E5AFA_19BC_37B7_41B8_3560EFE4E55C",
  "this.overlay_4D8CC301_19B4_D655_4192_9241C47FFC9E",
  "this.overlay_5D31A260_19FC_56D3_41B5_2FCA60107C21",
  "this.overlay_53AEA0AF_198C_73AD_41AC_E33A7501797F",
  "this.overlay_529FD9F8_198C_75B3_41AF_C3BED80D731E",
  "this.overlay_78D170BF_1994_D3AD_41AF_5F89CCB04B1F",
  "this.overlay_7AF3237E_1994_D6AE_4182_1C9D00417CFE",
  "this.overlay_71CD2DCF_1997_CDED_41B7_52ACA6D6A47B",
  "this.overlay_70A0A293_199C_5675_41B0_B124A8C4DC59",
  "this.overlay_710A5D1F_199C_D26D_419B_8771B94F87F7",
  "this.overlay_7436BCB4_1994_D3B3_41B7_E2535601BB16",
  "this.overlay_69B7FD06_198C_725F_418B_F724EA935E4A",
  "this.overlay_6CA107C5_198C_3DDD_41A4_F8903AFB5B0E",
  "this.overlay_765EA781_198C_3E55_4189_6BB70707128F",
  "this.overlay_1575C2AA_1A0C_9CDB_416F_4C539D9747AE",
  "this.overlay_1498D039_1A0C_BC38_41AA_5962EF8160AF",
  "this.overlay_1565C80A_1A0C_8BD8_41A6_5EDB92C1C9C0",
  "this.overlay_0AB7F20A_1A05_7FD8_41B5_FD6E4D1E9B5A",
  "this.overlay_15447CD9_1A04_8479_41A1_79D6601A3D1D",
  "this.overlay_15C82B05_1A0B_8DC8_41B8_A3D44E344BA4",
  "this.overlay_15C15163_1A0D_7C49_41B7_1A1EA064FFF0",
  "this.overlay_1550D25C_1A0D_9C7F_4198_183370ADB37B",
  "this.overlay_0A28160C_1A0F_87D8_41A8_C93241735699",
  "this.overlay_158A2272_1A0D_BC4B_416F_581294A10BB8",
  "this.overlay_15F2A1ED_1A0D_9C59_41B1_21CA73DDA163",
  "this.overlay_15D1B4D4_1A0C_844F_41A9_F52E3E1D322B",
  "this.overlay_0A1028E7_1A0B_8C49_41AD_87CA218CD114",
  "this.overlay_0A5292B7_1A0B_7CC9_41B1_EA242461998D",
  "this.overlay_095E7E8E_1A05_84DB_41A4_AFC05A1F6950",
  "this.overlay_15B6252A_1A04_85D8_41B7_483D3CD84C7B",
  "this.overlay_15FA9A86_1A07_8CCB_4179_5BF8774605D3",
  "this.overlay_1545219C_1A05_FCF8_41B4_F59AC6F9D1CE",
  "this.overlay_153AD345_1A3B_7C49_418C_9C7BB38960A6",
  "this.overlay_0BCCD221_1A05_9FC8_41A2_A767D920C013",
  "this.overlay_0B9B3A87_1A04_8CC9_41B5_D490319D40F6",
  "this.overlay_0AD19A4F_1A07_8C59_41B0_0068BB79EE53",
  "this.overlay_0AC4B112_1A04_FDC8_41A1_AD11D17B4D0B",
  "this.overlay_0AF0D596_1A05_84CB_41B9_B0AAC50CEF21"
 ],
 "partial": false,
 "hfov": 360,
 "pitch": 0,
 "class": "Panorama",
 "vfov": 180,
 "thumbnailUrl": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_t.jpg",
 "hfovMax": 130
},
{
 "id": "window_0B9E111C_1A04_BDF8_41B6_4D4AF333AB3E",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0B9FB11C_1A04_BDF8_41B0_E5BC40E1A4CD"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window14584"
 },
 "titleFontFamily": "Arial"
},
{
 "duration": 0,
 "id": "effect_0FDA162C_1980_E7E3_41B1_E072E17D3E02",
 "class": "FadeOutEffect",
 "easing": "linear"
},
{
 "id": "window_0A805D31_1A3B_85C8_41A3_16C7D8932ADF",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0A821D31_1A3B_85C8_41B7_DD4939726738"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window13294"
 },
 "titleFontFamily": "Arial"
},
{
 "items": [
  {
   "media": "this.video_1CA3469D_17F3_A3D0_41B1_B53CA5749C98",
   "start": "this.MainViewerVideoPlayer.set('displayPlaybackBar', true); this.changeBackgroundWhilePlay(this.mainPlayList, 0, '#000000'); this.pauseGlobalAudiosWhilePlayItem(this.mainPlayList, 0); this.updateVideoCues(this.mainPlayList, 0)",
   "begin": "this.fixTogglePlayPauseButton(this.MainViewerVideoPlayer)",
   "player": "this.MainViewerVideoPlayer",
   "class": "VideoPlayListItem"
  },
  "this.PanoramaPlayListItem_0EE83342_1A1D_9C48_4188_607BB3445E74",
  {
   "media": "this.panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7",
   "end": "this.trigger('tourEnded')",
   "begin": "this.setEndToItemIndex(this.mainPlayList, 2, 0)",
   "player": "this.MainViewerPanoramaPlayer",
   "class": "PanoramaPlayListItem",
   "camera": "this.panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_camera"
  }
 ],
 "id": "mainPlayList",
 "class": "PlayList"
},
{
 "viewerArea": "this.MainViewer",
 "id": "MainViewerVideoPlayer",
 "class": "VideoPlayer",
 "displayPlaybackBar": true
},
{
 "class": "PanoramaPlayer",
 "displayPlaybackBar": true,
 "touchControlMode": "drag_rotation",
 "viewerArea": "this.MainViewer",
 "gyroscopeVerticalDraggingEnabled": true,
 "id": "MainViewerPanoramaPlayer",
 "buttonZoomIn": "this.IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA",
 "mouseControlMode": "drag_acceleration"
},
{
 "id": "window_0B716160_1A05_9C47_41B1_D7DFF9F43733",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0B735160_1A05_9C47_41AB_3A0E01C99390"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window15250"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_41CCB917_198C_527D_41B2_A7BCD6ECE06A",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "title": "",
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "bodyBackgroundOpacity": 1,
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_41CC9918_198C_5273_41A8_048A66B6250C"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window25313"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_69EE01DA_1994_75F7_416F_F5985F3C9068",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "title": "",
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "bodyBackgroundOpacity": 1,
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_69E021DA_1994_75F7_4197_E42356C830A9"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window31114"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_7F78BE0A_199C_4E57_41A5_261D215FF2DB",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "title": "",
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "bodyBackgroundOpacity": 1,
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_7F790E0A_199C_4E57_4161_11E931C629B8"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window28305"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_0B3B7606_1A07_87C8_41B3_8138F82EDA9E",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0B393606_1A07_87C8_41B1_6FC1FA9AF677"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window15918"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_23E07BEA_19BC_55D7_4148_EB94176F53D8",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": "100%",
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "backgroundColor": [],
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "height": "100%",
 "title": "",
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "bodyBackgroundOpacity": 1,
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_23E23BEA_19BC_55D7_41B7_3E5D0DAEA4D0"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window8546"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_0ADA2259_1A07_9C78_4190_6E67FB65ADD8",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0AD8725A_1A07_9C78_418C_C82C8CBA096F"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window16598"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_0A6E99AB_1A3D_8CD9_41A3_513D3D187D4C",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0A7179AB_1A3D_8CD9_41B4_C2C9E9604695"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window11338"
 },
 "titleFontFamily": "Arial"
},
{
 "initialPosition": {
  "yaw": 0,
  "hfov": 120,
  "class": "PanoramaCameraPosition",
  "pitch": 0
 },
 "initialSequence": {
  "class": "PanoramaCameraSequence",
  "restartMovementOnUserInteraction": false,
  "movements": [
   {
    "yawSpeed": 7.96,
    "yawDelta": 18.5,
    "class": "DistancePanoramaCameraMovement",
    "easing": "cubic_in"
   },
   {
    "yawSpeed": 7.96,
    "yawDelta": 323,
    "class": "DistancePanoramaCameraMovement",
    "easing": "linear"
   },
   {
    "yawSpeed": 7.96,
    "yawDelta": 18.5,
    "class": "DistancePanoramaCameraMovement",
    "easing": "cubic_out"
   }
  ]
 },
 "automaticZoomSpeed": 10,
 "id": "panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_camera",
 "class": "PanoramaCamera"
},
{
 "cues": [
  {
   "end": "this.setComponentVisibility(this.IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA, false, 0, this.effect_0F8F00D4_19BE_DF36_41A3_BACA8AB736A2, 'hideEffect', false)",
   "endTime": 8.04,
   "begin": "this.setComponentVisibility(this.IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA, true, 0, this.effect_0EA78206_19A3_4312_41B4_32EC96EECA20, 'showEffect', false)",
   "class": "VideoCue",
   "startTime": 0
  }
 ],
 "thumbnailUrl": "media/video_1CA3469D_17F3_A3D0_41B1_B53CA5749C98_t.jpg",
 "id": "video_1CA3469D_17F3_A3D0_41B1_B53CA5749C98",
 "width": 1080,
 "loop": true,
 "scaleMode": "fit_inside",
 "label": "Shot01_Far_\u6c34\u5370",
 "class": "Video",
 "height": 1920,
 "video": {
  "width": 1080,
  "mp4Url": "media/video_1CA3469D_17F3_A3D0_41B1_B53CA5749C98.mp4",
  "class": "VideoResource",
  "height": 1920
 }
},
{
 "id": "window_0A437E46_1A04_8448_41A7_C63C7BB895F2",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0A4D1E46_1A04_8448_417B_73787C7B2BD5"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window13939"
 },
 "titleFontFamily": "Arial"
},
{
 "duration": 1000,
 "id": "effect_0EA78206_19A3_4312_41B4_32EC96EECA20",
 "class": "FadeInEffect",
 "easing": "cubic_in_out"
},
{
 "frames": [
  {
   "front": {
    "levels": [
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/f/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/f/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/f/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "top": {
    "levels": [
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/u/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/u/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/u/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "right": {
    "levels": [
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/r/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/r/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/r/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "back": {
    "levels": [
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/b/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/b/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/b/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "thumbnailUrl": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_t.jpg",
   "bottom": {
    "levels": [
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/d/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/d/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/d/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   },
   "class": "CubicPanoramaFrame",
   "left": {
    "levels": [
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/l/0/{row}_{column}.jpg",
      "width": 2048,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 4,
      "height": 2048,
      "colCount": 4
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/l/1/{row}_{column}.jpg",
      "width": 1024,
      "tags": "ondemand",
      "class": "TiledImageResourceLevel",
      "rowCount": 2,
      "height": 1024,
      "colCount": 2
     },
     {
      "url": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_0/l/2/{row}_{column}.jpg",
      "width": 512,
      "tags": [
       "ondemand",
       "preload"
      ],
      "class": "TiledImageResourceLevel",
      "rowCount": 1,
      "height": 512,
      "colCount": 1
     }
    ],
    "class": "ImageResource"
   }
  }
 ],
 "hfov": 360,
 "vfov": 180,
 "thumbnailUrl": "media/panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_t.jpg",
 "id": "panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7",
 "label": "Panarama_High_AE",
 "hfovMax": 130,
 "class": "Panorama",
 "pitch": 0,
 "partial": false
},
{
 "duration": 1000,
 "id": "effect_0F8F00D4_19BE_DF36_41A3_BACA8AB736A2",
 "class": "FadeOutEffect",
 "easing": "cubic_in_out"
},
{
 "initialPosition": {
  "yaw": 0,
  "class": "PanoramaCameraPosition",
  "pitch": 0
 },
 "initialSequence": {
  "class": "PanoramaCameraSequence",
  "restartMovementOnUserInteraction": false,
  "movements": [
   {
    "yawSpeed": 7.96,
    "yawDelta": 18.5,
    "class": "DistancePanoramaCameraMovement",
    "easing": "cubic_in"
   },
   {
    "yawSpeed": 7.96,
    "yawDelta": 323,
    "class": "DistancePanoramaCameraMovement",
    "easing": "linear"
   },
   {
    "yawSpeed": 7.96,
    "yawDelta": 18.5,
    "class": "DistancePanoramaCameraMovement",
    "easing": "cubic_out"
   }
  ]
 },
 "automaticZoomSpeed": 10,
 "id": "panorama_1AAC00C3_17F5_9FB0_41B7_15AED74509F7_camera",
 "class": "PanoramaCamera"
},
{
 "id": "window_0AE144C6_1A04_8448_4194_81E1DB261F29",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0AEF94C6_1A04_8448_41A1_B6B7BEA983ED"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window17269"
 },
 "titleFontFamily": "Arial"
},
{
 "id": "window_0B26C317_1A05_BDC9_41B2_BA7A5921B881",
 "backgroundOpacity": 1,
 "horizontalAlign": "center",
 "width": 400,
 "closeButtonIconHeight": 12,
 "overflow": "scroll",
 "titleFontColor": "#000000",
 "scrollBarMargin": 2,
 "class": "Window",
 "closeButtonRollOverBackgroundColor": [
  "#C13535"
 ],
 "titlePaddingRight": 5,
 "verticalAlign": "middle",
 "footerBackgroundColorDirection": "vertical",
 "paddingLeft": 0,
 "veilColorDirection": "horizontal",
 "headerBorderSize": 0,
 "minHeight": 20,
 "titlePaddingTop": 5,
 "propagateClick": false,
 "modal": true,
 "scrollBarWidth": 10,
 "closeButtonPressedBackgroundColorRatios": [
  0
 ],
 "footerBackgroundColor": [
  "#FFFFFF",
  "#EEEEEE",
  "#DDDDDD"
 ],
 "titlePaddingBottom": 5,
 "headerBorderColor": "#000000",
 "height": 600,
 "footerBackgroundColorRatios": [
  0,
  0.9,
  1
 ],
 "titleFontWeight": "normal",
 "minWidth": 20,
 "backgroundColor": [],
 "bodyBackgroundOpacity": 1,
 "borderSize": 0,
 "titleFontStyle": "normal",
 "headerPaddingRight": 10,
 "title": "",
 "bodyBackgroundColor": [
  "#FFFFFF",
  "#DDDDDD",
  "#FFFFFF"
 ],
 "headerPaddingLeft": 10,
 "bodyPaddingLeft": 5,
 "contentOpaque": false,
 "backgroundColorDirection": "vertical",
 "showEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundOpacity": 1,
 "footerHeight": 5,
 "scrollBarColor": "#000000",
 "veilShowEffect": {
  "duration": 500,
  "class": "FadeInEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColor": [
  "#DDDDDD",
  "#EEEEEE",
  "#FFFFFF"
 ],
 "bodyBorderColor": "#000000",
 "shadowBlurRadius": 6,
 "shadow": true,
 "bodyBackgroundColorRatios": [
  0,
  0.5,
  1
 ],
 "closeButtonBackgroundColorRatios": [],
 "shadowVerticalLength": 0,
 "scrollBarOpacity": 0.5,
 "titleTextDecoration": "none",
 "closeButtonIconLineWidth": 2,
 "bodyPaddingTop": 5,
 "layout": "vertical",
 "veilColor": [
  "#000000",
  "#000000"
 ],
 "children": [
  "this.htmlText_0B241317_1A05_BDC9_41B8_D8074192C67E"
 ],
 "scrollBarVisible": "rollOver",
 "shadowColor": "#000000",
 "shadowOpacity": 0.5,
 "closeButtonRollOverIconColor": "#FFFFFF",
 "paddingRight": 0,
 "headerPaddingTop": 10,
 "veilColorRatios": [
  0,
  1
 ],
 "titleFontSize": "1.29vmin",
 "borderRadius": 5,
 "bodyBackgroundColorDirection": "vertical",
 "closeButtonBorderRadius": 11,
 "backgroundColorRatios": [],
 "veilHideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "headerBackgroundColorRatios": [
  0,
  0.1,
  1
 ],
 "headerPaddingBottom": 10,
 "hideEffect": {
  "duration": 500,
  "class": "FadeOutEffect",
  "easing": "cubic_in_out"
 },
 "closeButtonPressedIconColor": "#FFFFFF",
 "closeButtonIconWidth": 12,
 "veilOpacity": 0.4,
 "closeButtonPressedBackgroundColor": [
  "#3A1D1F"
 ],
 "paddingBottom": 0,
 "gap": 10,
 "paddingTop": 0,
 "closeButtonIconColor": "#000000",
 "titlePaddingLeft": 5,
 "bodyPaddingBottom": 5,
 "shadowHorizontalLength": 3,
 "closeButtonRollOverBackgroundColorRatios": [
  0
 ],
 "headerVerticalAlign": "middle",
 "bodyPaddingRight": 5,
 "headerBackgroundColorDirection": "vertical",
 "bodyBorderSize": 0,
 "shadowSpread": 1,
 "closeButtonBackgroundColor": [],
 "data": {
  "name": "Window17946"
 },
 "titleFontFamily": "Arial"
},
{
 "progressBarBorderSize": 0,
 "id": "MainViewer",
 "left": 0,
 "width": "100%",
 "playbackBarProgressBorderRadius": 0,
 "toolTipShadowOpacity": 0,
 "class": "ViewerArea",
 "progressBarBorderRadius": 0,
 "toolTipFontStyle": "normal",
 "playbackBarHeadShadowHorizontalLength": 0,
 "playbackBarBorderRadius": 0,
 "paddingLeft": 0,
 "playbackBarProgressBorderColor": "#000000",
 "minHeight": 50,
 "toolTipFontFamily": "微软雅黑",
 "propagateClick": false,
 "toolTipTextShadowOpacity": 0,
 "playbackBarHeadBorderRadius": 0,
 "progressLeft": 0,
 "playbackBarHeadBorderSize": 0,
 "playbackBarProgressOpacity": 1,
 "playbackBarBorderSize": 0,
 "transitionDuration": 500,
 "minWidth": 100,
 "playbackBarBackgroundOpacity": 1,
 "height": "100%",
 "toolTipFontColor": "#606060",
 "playbackBarHeadBorderColor": "#000000",
 "vrPointerSelectionColor": "#FF6600",
 "borderSize": 0,
 "playbackBarHeadShadowColor": "#000000",
 "toolTipBackgroundColor": "#F6F6F6",
 "progressRight": 0,
 "firstTransitionDuration": 0,
 "progressOpacity": 1,
 "playbackBarHeadBackgroundColor": [
  "#111111",
  "#666666"
 ],
 "vrPointerSelectionTime": 2000,
 "progressBarBackgroundColorDirection": "vertical",
 "progressBottom": 0,
 "progressHeight": 10,
 "playbackBarHeadShadow": true,
 "shadow": false,
 "playbackBarHeadBackgroundColorDirection": "vertical",
 "progressBackgroundOpacity": 1,
 "playbackBarProgressBackgroundColor": [
  "#3399FF"
 ],
 "playbackBarOpacity": 1,
 "toolTipPaddingRight": 6,
 "playbackBarHeadShadowOpacity": 0.7,
 "toolTipBorderSize": 0,
 "toolTipPaddingLeft": 6,
 "toolTipPaddingTop": 4,
 "vrPointerColor": "#FFFFFF",
 "toolTipDisplayTime": 600,
 "progressBarOpacity": 1,
 "playbackBarBorderColor": "#FFFFFF",
 "progressBorderSize": 0,
 "transitionMode": "blending",
 "displayTooltipInTouchScreens": true,
 "toolTipBorderRadius": 3,
 "paddingRight": 0,
 "progressBorderRadius": 0,
 "borderRadius": 0,
 "playbackBarProgressBackgroundColorRatios": [
  0
 ],
 "playbackBarLeft": 0,
 "progressBackgroundColorRatios": [
  0
 ],
 "playbackBarHeadHeight": 15,
 "top": 0,
 "playbackBarHeadShadowBlurRadius": 3,
 "playbackBarHeadBackgroundColorRatios": [
  0,
  1
 ],
 "progressBarBorderColor": "#000000",
 "toolTipBorderColor": "#767676",
 "progressBarBackgroundColorRatios": [
  0
 ],
 "progressBackgroundColorDirection": "vertical",
 "toolTipShadowSpread": 0,
 "toolTipShadowBlurRadius": 3,
 "playbackBarBottom": 5,
 "toolTipTextShadowColor": "#000000",
 "toolTipOpacity": 0.5,
 "playbackBarHeadOpacity": 1,
 "progressBarBackgroundColor": [
  "#3399FF"
 ],
 "paddingTop": 0,
 "paddingBottom": 0,
 "toolTipPaddingBottom": 4,
 "playbackBarHeadShadowVerticalLength": 0,
 "toolTipFontSize": "20px",
 "toolTipTextShadowBlurRadius": 3,
 "progressBackgroundColor": [
  "#FFFFFF"
 ],
 "playbackBarProgressBackgroundColorDirection": "vertical",
 "toolTipShadowColor": "#333333",
 "playbackBarBackgroundColor": [
  "#FFFFFF"
 ],
 "progressBorderColor": "#000000",
 "data": {
  "name": "Main Viewer"
 },
 "playbackBarHeight": 10,
 "toolTipFontWeight": "normal",
 "playbackBarBackgroundColorDirection": "vertical",
 "playbackBarHeadWidth": 6,
 "playbackBarProgressBorderSize": 0,
 "playbackBarRight": 0
},
{
 "horizontalAlign": "center",
 "id": "IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA",
 "left": "47.7%",
 "width": 51.65,
 "backgroundOpacity": 0,
 "verticalAlign": "middle",
 "class": "IconButton",
 "borderRadius": 0,
 "paddingLeft": 0,
 "paddingRight": 0,
 "minHeight": 0,
 "rollOverIconURL": "skin/IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA_rollover.png",
 "propagateClick": false,
 "top": "48%",
 "height": 43.35,
 "minWidth": 0,
 "mode": "push",
 "paddingTop": 0,
 "paddingBottom": 0,
 "transparencyActive": true,
 "borderSize": 0,
 "click": "this.setPanoramaCameraWithCurrentSpot(this.PanoramaPlayListItem_0EE83342_1A1D_9C48_4188_607BB3445E74);; this.mainPlayList.set('selectedIndex', 1); this.setComponentVisibility(this.IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA, false, 0, this.effect_0FDA162C_1980_E7E3_41B1_E072E17D3E02, 'hideEffect', false)",
 "iconURL": "skin/IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA.png",
 "pressedIconURL": "skin/IconButton_0F01FB6F_19A2_C112_419F_870ED34C8CAA_pressed.png",
 "data": {
  "name": "ButtonGlow"
 },
 "shadow": false,
 "visible": false,
 "cursor": "hand"
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 57.77,
   "yaw": 0.19,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_2_1_0_map.gif",
      "width": 200,
      "class": "ImageResourceLevel",
      "height": 51
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -27.77
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_23E07BEA_19BC_55D7_4148_EB94176F53D8, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": true,
 "items": [
  {
   "hfov": 57.77,
   "class": "HotspotPanoramaOverlayImage",
   "roll": 0,
   "distance": 50,
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_2_0.png",
      "width": 1053,
      "class": "ImageResourceLevel",
      "height": 270
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -27.77,
   "yaw": 0.19
  }
 ],
 "id": "overlay_276E5AFA_19BC_37B7_41B8_3560EFE4E55C",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 15.67,
   "yaw": -19.68,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_7_1_0_map.gif",
      "width": 131,
      "class": "ImageResourceLevel",
      "height": 159
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 12.07
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_41CCB917_198C_527D_41B2_A7BCD6ECE06A, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": true,
 "id": "overlay_4D8CC301_19B4_D655_4192_9241C47FFC9E",
 "data": {
  "label": "\u57ce\u5e02\u71c3\u6c14\u906e\u7f69"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 11.31,
   "yaw": -20.17,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_3_0_map.gif",
      "width": 70,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 17.65
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_3_0.png",
      "width": 197,
      "class": "ImageResourceLevel",
      "height": 45
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 17.65,
   "hfov": 11.31,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": -20.17
  }
 ],
 "id": "overlay_5D31A260_19FC_56D3_41B5_2FCA60107C21",
 "data": {
  "label": "\u4e2d\u56fd\u57ce\u5e02\u71c3\u6c14\u534f\u4f1a"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 7.64,
   "yaw": -19.49,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_5_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 11.67
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "toolTip": "\u000a",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_57C1C6F5_198F_DFB2_4174_B9063296D61D",
   "pitch": 11.67,
   "hfov": 7.64,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": -19.49
  }
 ],
 "id": "overlay_53AEA0AF_198C_73AD_41AC_E33A7501797F",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 4.3,
   "yaw": 28.11,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_6_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -23.21
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_4D24CDD1_19B4_4DF5_41B0_12E2CFF6CE4A",
   "pitch": -23.21,
   "hfov": 4.3,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": 28.11
  }
 ],
 "id": "overlay_529FD9F8_198C_75B3_41AF_C3BED80D731E",
 "data": {
  "label": "Info Red 04"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 16,
   "yaw": 47.89,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_8_1_0_map.gif",
      "width": 134,
      "class": "ImageResourceLevel",
      "height": 145
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -2.59
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_7F78BE0A_199C_4E57_41A5_261D215FF2DB, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": true,
 "id": "overlay_78D170BF_1994_D3AD_41AF_5F89CCB04B1F",
 "data": {
  "label": "\u5317\u4eac\u5e02\u71c3\u6c14\u96c6\u56e2\u906e\u7f69"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 5.5,
   "yaw": 47.95,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_9_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -4.03
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_74CDF2F2_1995_F7B7_41B8_83FC58390DEF",
   "pitch": -4.03,
   "hfov": 5.5,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": 47.95
  }
 ],
 "id": "overlay_7AF3237E_1994_D6AE_4182_1C9D00417CFE",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 11.82,
   "yaw": 48.68,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_10_0_map.gif",
      "width": 80,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 3.99
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_10_0.png",
      "width": 197,
      "class": "ImageResourceLevel",
      "height": 39
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 3.99,
   "hfov": 11.82,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": 48.68
  }
 ],
 "id": "overlay_71CD2DCF_1997_CDED_41B7_52ACA6D6A47B",
 "data": {
  "label": "\u5317\u4eac\u5e02\u71c3\u6c14\u96c6\u56e2"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 5.5,
   "yaw": 78.11,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_11_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 4.1
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_74CD62F3_1995_F7B5_41B3_DEA1634EA4A3",
   "pitch": 4.1,
   "hfov": 5.5,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": 78.11
  }
 ],
 "id": "overlay_70A0A293_199C_5675_41B0_B124A8C4DC59",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 17.35,
   "yaw": 78.57,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_12_0_map.gif",
      "width": 102,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 0.08
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_12_0.png",
      "width": 289,
      "class": "ImageResourceLevel",
      "height": 45
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 0.08,
   "hfov": 17.35,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": 78.57
  }
 ],
 "id": "overlay_710A5D1F_199C_D26D_419B_8771B94F87F7",
 "data": {
  "label": "\u4e2d\u56fd\u71c3\u6c14\u63a7\u80a1\u6709\u9650\u516c\u53f8"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 19.75,
   "yaw": 75.69,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_13_1_0_map.gif",
      "width": 166,
      "class": "ImageResourceLevel",
      "height": 197
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 0.08
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_69EE01DA_1994_75F7_416F_F5985F3C9068, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_7436BCB4_1994_D3B3_41B7_E2535601BB16",
 "data": {
  "label": "\u4e2d\u56fd\u71c3\u6c14\u906e\u7f69"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 5.34,
   "yaw": 98.01,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_14_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 8.3
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_6EC31A6E_198C_36AF_41A5_CAFBED10FB86",
   "pitch": 8.3,
   "hfov": 5.34,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": 98.01
  }
 ],
 "id": "overlay_69B7FD06_198C_725F_418B_F724EA935E4A",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 8.51,
   "yaw": 99.35,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_15_0_map.gif",
      "width": 40,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 13.17
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_15_0.png",
      "width": 145,
      "class": "ImageResourceLevel",
      "height": 57
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 13.17,
   "hfov": 8.51,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": 99.35
  }
 ],
 "id": "overlay_6CA107C5_198C_3DDD_41A4_F8903AFB5B0E",
 "data": {
  "label": "\u5343\u5609\u79d1\u6280\u00ae"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 10.22,
   "yaw": 97.29,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_16_1_0_map.gif",
      "width": 85,
      "class": "ImageResourceLevel",
      "height": 125
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 9.27
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0A6E99AB_1A3D_8CD9_41A3_513D3D187D4C, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_765EA781_198C_3E55_4189_6BB70707128F",
 "data": {
  "label": "\u5343\u5609\u79d1\u6280\u906e\u7f69"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 4.54,
   "yaw": 106.08,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_17_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -0.84
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0ECC927A_1A0C_BC3B_419E_33D64FB4FEA7",
   "pitch": -0.84,
   "hfov": 4.54,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": 106.08
  }
 ],
 "id": "overlay_1575C2AA_1A0C_9CDB_416F_4C539D9747AE",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 4.65,
   "yaw": 106.46,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_18_0_map.gif",
      "width": 28,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -3.45
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_18_0.png",
      "width": 77,
      "class": "ImageResourceLevel",
      "height": 44
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -3.45,
   "hfov": 4.65,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": 106.46
  }
 ],
 "id": "overlay_1498D039_1A0C_BC38_41AA_5962EF8160AF",
 "data": {
  "label": "\u79e6\u5ddd"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 9.08,
   "yaw": 105.37,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_19_1_0_map.gif",
      "width": 75,
      "class": "ImageResourceLevel",
      "height": 159
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -1.18
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0A805D31_1A3B_85C8_41A3_16C7D8932ADF, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_1565C80A_1A0C_8BD8_41A6_5EDB92C1C9C0",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 9.19,
   "yaw": 130.99,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_20_0_map.gif",
      "width": 23,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 2.92
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_20_0.png",
      "width": 153,
      "class": "ImageResourceLevel",
      "height": 105
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 2.92,
   "hfov": 9.19,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": 130.99
  }
 ],
 "id": "overlay_0AB7F20A_1A05_7FD8_41B5_FD6E4D1E9B5A",
 "data": {
  "label": "\u8fbd\u5b81\u601d\u51ef\u79d1\u6280\u000d\u80a1\u4efd\u6709\u9650\u516c\u53f8"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 3.71,
   "yaw": 132.28,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_21_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 7.46
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0EC3927B_1A0C_BC39_41B2_2B66EE39DA33",
   "pitch": 7.46,
   "hfov": 3.71,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": 132.28
  }
 ],
 "id": "overlay_15447CD9_1A04_8479_41A1_79D6601A3D1D",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 12.36,
   "yaw": 129.6,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_22_1_0_map.gif",
      "width": 103,
      "class": "ImageResourceLevel",
      "height": 143
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 6.25
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0A437E46_1A04_8448_41A7_C63C7BB895F2, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_15C82B05_1A0B_8DC8_41B8_A3D44E344BA4",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 5.15,
   "yaw": 147.99,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_23_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -11.57
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0EC2D27B_1A0C_BC39_4198_07C6E5418359",
   "pitch": -11.57,
   "hfov": 5.15,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": 147.99
  }
 ],
 "id": "overlay_15C15163_1A0D_7C49_41B7_1A1EA064FFF0",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 6.41,
   "yaw": 147.32,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_24_0_map.gif",
      "width": 39,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 0.68
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_24_0.png",
      "width": 106,
      "class": "ImageResourceLevel",
      "height": 43
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 0.68,
   "hfov": 6.41,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": 147.32
  }
 ],
 "id": "overlay_1550D25C_1A0D_9C7F_4198_183370ADB37B",
 "data": {
  "label": "\u57ce\u5e02\u71c3\u6c14"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 33,
   "yaw": 150.7,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_25_1_0_map.gif",
      "width": 200,
      "class": "ImageResourceLevel",
      "height": 156
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -4.11
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0B9E111C_1A04_BDF8_41B6_4D4AF333AB3E, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_0A28160C_1A0F_87D8_41A8_C93241735699",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 5.14,
   "yaw": -174.16,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_26_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 2.88
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0EC1C27C_1A0C_BC3F_418F_06349D374728",
   "pitch": 2.88,
   "hfov": 5.14,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": -174.16
  }
 ],
 "id": "overlay_158A2272_1A0D_BC4B_416F_581294A10BB8",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 6.02,
   "yaw": -150.95,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_27_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 2.47
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0EC1827C_1A0C_BC3F_4187_390DF172AC31",
   "pitch": 2.47,
   "hfov": 6.02,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": -150.95
  }
 ],
 "id": "overlay_15F2A1ED_1A0D_9C59_41B1_21CA73DDA163",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 7.01,
   "yaw": -123.51,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_28_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -3.56
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0EC1427C_1A0C_BC3F_41B7_19BF43BF50BA",
   "pitch": -3.56,
   "hfov": 7.01,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": -123.51
  }
 ],
 "id": "overlay_15D1B4D4_1A0C_844F_41A9_F52E3E1D322B",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 6.42,
   "yaw": -66.38,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_29_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 12.56
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0EC1127C_1A0C_BC3F_41A3_EEBA5893F0E6",
   "pitch": 12.56,
   "hfov": 6.42,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": -66.38
  }
 ],
 "id": "overlay_0A1028E7_1A0B_8C49_41AD_87CA218CD114",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 7.79,
   "yaw": -35.84,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_30_0_0_map.gif",
      "width": 16,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 19.99
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": "this.AnimatedImageResource_0EC0D27C_1A0C_BC3F_41A1_01C9106CC17A",
   "pitch": 19.99,
   "hfov": 7.79,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 100,
   "yaw": -35.84
  }
 ],
 "id": "overlay_0A5292B7_1A0B_7CC9_41B1_EA242461998D",
 "data": {
  "label": "Circle Arrow 01"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 6.09,
   "yaw": -32.75,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_31_0_map.gif",
      "width": 39,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 27.89
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_31_0.png",
      "width": 114,
      "class": "ImageResourceLevel",
      "height": 46
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 27.89,
   "hfov": 6.09,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": -32.75
  }
 ],
 "id": "overlay_095E7E8E_1A05_84DB_41A4_AFC05A1F6950",
 "data": {
  "label": "ENN\u65b0\u5965"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 6.47,
   "yaw": -66.4,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_32_0_map.gif",
      "width": 50,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 22.04
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_32_0.png",
      "width": 116,
      "class": "ImageResourceLevel",
      "height": 37
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 22.04,
   "hfov": 6.47,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": -66.4
  }
 ],
 "id": "overlay_15B6252A_1A04_85D8_41B7_483D3CD84C7B",
 "data": {
  "label": "\u4e2d\u534e\u7164\u6c14"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 14.46,
   "yaw": -129.15,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_33_0_map.gif",
      "width": 69,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 7.53
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_33_0.png",
      "width": 243,
      "class": "ImageResourceLevel",
      "height": 56
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 7.53,
   "hfov": 14.46,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": -129.15
  }
 ],
 "id": "overlay_15FA9A86_1A07_8CCB_4179_5BF8774605D3",
 "data": {
  "label": "GoldCard\u91d1\u5361\u667a\u80fd"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 16.34,
   "yaw": -147.58,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_34_0_map.gif",
      "width": 98,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 22.16
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_34_0.png",
      "width": 294,
      "class": "ImageResourceLevel",
      "height": 48
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 22.16,
   "hfov": 16.34,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": -147.58
  }
 ],
 "id": "overlay_1545219C_1A05_FCF8_41B4_F59AC6F9D1CE",
 "data": {
  "label": "\u5317\u4eac\u5317\u71c3\u5b9e\u4e1a\u96c6\u56e2\u6709\u9650\u516c\u53f8"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 17.74,
   "yaw": -168.58,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_35_0_map.gif",
      "width": 107,
      "class": "ImageResourceLevel",
      "height": 16
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -2.09
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "mapColor": "#FF0000",
   "class": "HotspotPanoramaOverlayArea"
  }
 ],
 "rollOverDisplay": false,
 "enabledInCardboard": true,
 "items": [
  {
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_35_0.png",
      "width": 295,
      "class": "ImageResourceLevel",
      "height": 44
     }
    ],
    "class": "ImageResource"
   },
   "pitch": -2.09,
   "hfov": 17.74,
   "class": "HotspotPanoramaOverlayImage",
   "distance": 50,
   "yaw": -168.58
  }
 ],
 "id": "overlay_153AD345_1A3B_7C49_418C_9C7BB38960A6",
 "data": {
  "label": "\u6210\u90fd\u71c3\u6c14\u96c6\u56e2\u80a1\u4efd\u6709\u9650\u516c\u53f8"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 20.12,
   "yaw": -169.28,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_36_1_0_map.gif",
      "width": 169,
      "class": "ImageResourceLevel",
      "height": 197
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 2.86
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0B716160_1A05_9C47_41B1_D7DFF9F43733, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_0BCCD221_1A05_9FC8_41A2_A767D920C013",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 20.01,
   "yaw": -148.19,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_37_1_0_map.gif",
      "width": 117,
      "class": "ImageResourceLevel",
      "height": 200
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 8.37
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0B3B7606_1A07_87C8_41B3_8138F82EDA9E, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_0B9B3A87_1A04_8CC9_41B5_D490319D40F6",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 23.72,
   "yaw": -125.52,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_38_1_0_map.gif",
      "width": 85,
      "class": "ImageResourceLevel",
      "height": 200
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 5.34
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0ADA2259_1A07_9C78_4190_6E67FB65ADD8, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_0AD19A4F_1A07_8C59_41B0_0068BB79EE53",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 13.99,
   "yaw": -67.04,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_39_1_0_map.gif",
      "width": 117,
      "class": "ImageResourceLevel",
      "height": 191
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 13.61
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0AE144C6_1A04_8448_4194_81E1DB261F29, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_0AC4B112_1A04_FDC8_41A1_AD11D17B4D0B",
 "data": {
  "label": "Polygon"
 }
},
{
 "useHandCursor": true,
 "maps": [
  {
   "hfov": 16.35,
   "yaw": -37.88,
   "class": "HotspotPanoramaOverlayMap",
   "image": {
    "levels": [
     {
      "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_40_1_0_map.gif",
      "width": 118,
      "class": "ImageResourceLevel",
      "height": 200
     }
    ],
    "class": "ImageResource"
   },
   "pitch": 22.07
  }
 ],
 "class": "HotspotPanoramaOverlay",
 "areas": [
  {
   "click": "this.showWindow(this.window_0B26C317_1A05_BDC9_41B2_BA7A5921B881, null, false)",
   "class": "HotspotPanoramaOverlayArea",
   "mapColor": "#FF0000"
  }
 ],
 "rollOverDisplay": false,
 "id": "overlay_0AF0D596_1A05_84CB_41B9_B0AAC50CEF21",
 "data": {
  "label": "Polygon"
 }
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0B9FB11C_1A04_BDF8_41B0_E5BC40E1A4CD",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u4e3b\u529e\u65b9\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u57ce\u5e02\u71c3\u6c14</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u4e3b\u529e\u65b9\u4ecb\u7ecd</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:left;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"> \u2022 \u4e3b\u7ba1\uff1a\u4e2d\u534e\u4eba\u6c11\u5171\u548c\u56fd\u4f4f\u623f\u548c\u57ce\u4e61\u5efa\u8bbe\u90e8 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:left;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"> \u2022 \u4e3b\u529e\uff1a\u4e2d\u56fd\u57ce\u5e02\u71c3\u6c14\u534f\u4f1a</SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText14585"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0A821D31_1A3B_85C8_41B7_DD4939726738",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u79e6\u5ddd</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText13295"
 },
 "shadow": false
},
{
 "media": "this.panorama_17FF3516_1812_A6D3_41A4_6B9087789C50",
 "class": "PanoramaPlayListItem",
 "begin": "this.setEndToItemIndex(this.mainPlayList, 1, 2)",
 "player": "this.MainViewerPanoramaPlayer",
 "id": "PanoramaPlayListItem_0EE83342_1A1D_9C48_4188_607BB3445E74",
 "camera": "this.panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_camera"
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0B735160_1A05_9C47_41AB_3A0E01C99390",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u6210\u90fd\u71c3\u6c14\u96c6\u56e2\u80a1\u4efd\u6709\u9650\u516c\u53f8</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText15251"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_41CC9918_198C_5273_41A8_048A66B6250C",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u4e2d\u56fd\u57ce\u5e02\u71c3\u6c14\u534f\u4f1a</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1 Light';\"><B>CHINA GAS ASSOCIATION</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u534f\u4f1a\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText25314"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_69E021DA_1994_75F7_4197_E42356C830A9",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u4e2d\u56fd\u71c3\u6c14\u63a7\u80a1\u6709\u9650\u516c\u53f8</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1 Light';\"><B>CHINA GAS</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText31115"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_7F790E0A_199C_4E57_4161_11E931C629B8",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:left;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5317\u4eac\u5e02\u71c3\u6c14\u96c6\u56e2\u6709\u9650\u8d23\u4efb\u516c\u53f8</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1 Light';\"><B>BEIJING GAS </B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:left;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText28306"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0B393606_1A07_87C8_41B1_6FC1FA9AF677",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5317\u4eac\u5317\u71c3\u5b9e\u4e1a\u96c6\u56e2\u6709\u9650\u516c\u53f8</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText15919"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_23E23BEA_19BC_55D7_41B7_3E5D0DAEA4D0",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:16px;\"><B>\u5c55\u4f1a\u8be6\u60c5</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>2020\u5e74\u7b2c23\u5c4a</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u4e2d\u56fd\u56fd\u9645\u71c3\u6c14\u3001\u4f9b\u70ed\u6280\u672f\u4e0e\u8bbe\u5907\u5c55\u89c8\u4f1a</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:16px;font-family:'\u5fae\u8f6f\u96c5\u9ed1 Light';\"><B>GAS Heating CHINA 2020</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:left;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:16px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u4f1a\u65f6\u95f4\uff1a</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:16px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u53c2\u5c55\u5355\u4f4d</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:16px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u4f1a\u4ecb\u7ecd</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><p STYLE=\"margin:0; line-height:16px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText8547"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0AD8725A_1A07_9C78_418C_C82C8CBA096F",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>Goldcard\u91d1\u5361\u667a\u80fd</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText16599"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0A7179AB_1A3D_8CD9_41B4_C2C9E9604695",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5343\u5609\u79d1\u6280\u00ae</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText11339"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0A4D1E46_1A04_8448_417B_73787C7B2BD5",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u8fbd\u5b81\u601d\u51ef\u79d1\u6280\u80a1\u4efd\u6709\u9650\u516c\u53f8</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText13940"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0AEF94C6_1A04_8448_41A1_B6B7BEA983ED",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u4e2d\u534e\u7164\u6c14</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText17270"
 },
 "shadow": false
},
{
 "scrollBarOpacity": 0.5,
 "id": "htmlText_0B241317_1A05_BDC9_41B8_D8074192C67E",
 "backgroundOpacity": 0,
 "width": "100%",
 "scrollBarVisible": "rollOver",
 "scrollBarMargin": 2,
 "class": "HTMLText",
 "borderRadius": 0,
 "paddingLeft": 10,
 "paddingRight": 10,
 "minHeight": 0,
 "scrollBarWidth": 10,
 "propagateClick": false,
 "height": "100%",
 "minWidth": 0,
 "paddingTop": 10,
 "paddingBottom": 10,
 "borderSize": 0,
 "html": "<div style=\"text-align:left; color:#000; \"><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:18px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u5546\u4e3b\u9875</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:18px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:20px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>ENN\u65b0\u5965</B></SPAN></SPAN></DIV><p STYLE=\"margin:0; line-height:14px;\"><BR STYLE=\"letter-spacing:0px;color:#000000;font-size:12px;font-family:Arial, Helvetica, sans-serif;\"/></p><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u516c\u53f8\u4ecb\u7ecd</B></SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN></SPAN></DIV><DIV STYLE=\"text-align:center;\"><SPAN STYLE=\"letter-spacing:0px;color:#000000;font-family:Arial, Helvetica, sans-serif;\"><SPAN STYLE=\"font-size:15px;\"> \u2022 </SPAN><SPAN STYLE=\"font-size:14px;font-family:'\u5fae\u8f6f\u96c5\u9ed1';\"><B>\u5c55\u54c1</B></SPAN></SPAN></DIV></div>",
 "scrollBarColor": "#000000",
 "data": {
  "name": "HTMLText17947"
 },
 "shadow": false
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_5_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_57C1C6F5_198F_DFB2_4174_B9063296D61D",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_6_0.png",
   "width": 660,
   "class": "ImageResourceLevel",
   "height": 990
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_4D24CDD1_19B4_4DF5_41B0_12E2CFF6CE4A",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_9_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_74CDF2F2_1995_F7B7_41B8_83FC58390DEF",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_11_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_74CD62F3_1995_F7B5_41B3_DEA1634EA4A3",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_14_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_6EC31A6E_198C_36AF_41A5_CAFBED10FB86",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_17_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0ECC927A_1A0C_BC3B_419E_33D64FB4FEA7",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_21_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0EC3927B_1A0C_BC39_41B2_2B66EE39DA33",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_23_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0EC2D27B_1A0C_BC39_4198_07C6E5418359",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_26_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0EC1C27C_1A0C_BC3F_418F_06349D374728",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_27_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0EC1827C_1A0C_BC3F_4187_390DF172AC31",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_28_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0EC1427C_1A0C_BC3F_41B7_19BF43BF50BA",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_29_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0EC1127C_1A0C_BC3F_41A3_EEBA5893F0E6",
 "rowCount": 6,
 "colCount": 4
},
{
 "levels": [
  {
   "url": "media/panorama_17FF3516_1812_A6D3_41A4_6B9087789C50_0_HS_30_0.png",
   "width": 800,
   "class": "ImageResourceLevel",
   "height": 1200
  }
 ],
 "frameCount": 24,
 "class": "AnimatedImageResource",
 "frameDuration": 41,
 "id": "AnimatedImageResource_0EC0D27C_1A0C_BC3F_41A1_01C9106CC17A",
 "rowCount": 6,
 "colCount": 4
}],
 "backgroundPreloadEnabled": true,
 "paddingTop": 0,
 "paddingBottom": 0,
 "borderSize": 0,
 "creationPolicy": "inAdvance",
 "height": "100%",
 "contentOpaque": false,
 "scripts": {
  "getOverlays": function(media){  switch(media.get('class')){ case 'Panorama': var overlays = media.get('overlays').concat() || []; var frames = media.get('frames'); for(var j = 0; j<frames.length; ++j){ overlays = overlays.concat(frames[j].get('overlays') || []); } return overlays; case 'Video360': case 'Map': return media.get('overlays') || []; default: return []; } },
  "setMainMediaByName": function(name){  var items = this.mainPlayList.get('items'); for(var i = 0; i<items.length; ++i){ var item = items[i]; if(item.get('media').get('label') == name) { this.mainPlayList.set('selectedIndex', i); return item; } } },
  "showComponentsWhileMouseOver": function(parentComponent, components, durationVisibleWhileOut){  var setVisibility = function(visible){ for(var i = 0, length = components.length; i<length; i++){ var component = components[i]; if(component.get('class') == 'HTMLText' && (component.get('html') == '' || component.get('html') == undefined)) { continue; } component.set('visible', visible); } }; if (this.rootPlayer.get('touchDevice') == true){ setVisibility(true); } else { var timeoutID = -1; var rollOverFunction = function(){ setVisibility(true); if(timeoutID >= 0) clearTimeout(timeoutID); parentComponent.unbind('rollOver', rollOverFunction, this); parentComponent.bind('rollOut', rollOutFunction, this); }; var rollOutFunction = function(){ var timeoutFunction = function(){ setVisibility(false); parentComponent.unbind('rollOver', rollOverFunction, this); }; parentComponent.unbind('rollOut', rollOutFunction, this); parentComponent.bind('rollOver', rollOverFunction, this); timeoutID = setTimeout(timeoutFunction, durationVisibleWhileOut); }; parentComponent.bind('rollOver', rollOverFunction, this); } },
  "getCurrentPlayers": function(){  var players = this.getByClassName('PanoramaPlayer'); players = players.concat(this.getByClassName('VideoPlayer')); players = players.concat(this.getByClassName('Video360Player')); players = players.concat(this.getByClassName('PhotoAlbumPlayer')); return players; },
  "setCameraSameSpotAsMedia": function(camera, media){  var player = this.getCurrentPlayerWithMedia(media); if(player != undefined) { var position = camera.get('initialPosition'); position.set('yaw', player.get('yaw')); position.set('pitch', player.get('pitch')); position.set('hfov', player.get('hfov')); } },
  "showPopupMedia": function(w, media, playList, popupMaxWidth, popupMaxHeight, autoCloseWhenFinished, stopAudios){  var self = this; var closeFunction = function(){ playList.set('selectedIndex', -1); self.MainViewer.set('toolTipEnabled', true); if(stopAudios) { self.resumeGlobalAudios(); } this.resumePlayers(playersPaused, !stopAudios); if(isVideo) { this.unbind('resize', resizeFunction, this); } w.unbind('close', closeFunction, this); }; var endFunction = function(){ w.hide(); }; var resizeFunction = function(){ var getWinValue = function(property){ return w.get(property) || 0; }; var parentWidth = self.get('actualWidth'); var parentHeight = self.get('actualHeight'); var mediaWidth = self.getMediaWidth(media); var mediaHeight = self.getMediaHeight(media); var popupMaxWidthNumber = parseFloat(popupMaxWidth) / 100; var popupMaxHeightNumber = parseFloat(popupMaxHeight) / 100; var windowWidth = popupMaxWidthNumber * parentWidth; var windowHeight = popupMaxHeightNumber * parentHeight; var footerHeight = getWinValue('footerHeight'); var headerHeight = getWinValue('headerHeight'); if(!headerHeight) { var closeButtonHeight = getWinValue('closeButtonIconHeight') + getWinValue('closeButtonPaddingTop') + getWinValue('closeButtonPaddingBottom'); var titleHeight = self.getPixels(getWinValue('titleFontSize')) + getWinValue('titlePaddingTop') + getWinValue('titlePaddingBottom'); headerHeight = closeButtonHeight > titleHeight ? closeButtonHeight : titleHeight; headerHeight += getWinValue('headerPaddingTop') + getWinValue('headerPaddingBottom'); } var contentWindowWidth = windowWidth - getWinValue('bodyPaddingLeft') - getWinValue('bodyPaddingRight') - getWinValue('paddingLeft') - getWinValue('paddingRight'); var contentWindowHeight = windowHeight - headerHeight - footerHeight - getWinValue('bodyPaddingTop') - getWinValue('bodyPaddingBottom') - getWinValue('paddingTop') - getWinValue('paddingBottom'); var parentAspectRatio = contentWindowWidth / contentWindowHeight; var mediaAspectRatio = mediaWidth / mediaHeight; if(parentAspectRatio > mediaAspectRatio) { windowWidth = contentWindowHeight * mediaAspectRatio + getWinValue('bodyPaddingLeft') + getWinValue('bodyPaddingRight') + getWinValue('paddingLeft') + getWinValue('paddingRight'); } else { windowHeight = contentWindowWidth / mediaAspectRatio + headerHeight + footerHeight + getWinValue('bodyPaddingTop') + getWinValue('bodyPaddingBottom') + getWinValue('paddingTop') + getWinValue('paddingBottom'); } if(windowWidth > parentWidth * popupMaxWidthNumber) { windowWidth = parentWidth * popupMaxWidthNumber; } if(windowHeight > parentHeight * popupMaxHeightNumber) { windowHeight = parentHeight * popupMaxHeightNumber; } w.set('width', windowWidth); w.set('height', windowHeight); w.set('x', (parentWidth - getWinValue('actualWidth')) * 0.5); w.set('y', (parentHeight - getWinValue('actualHeight')) * 0.5); }; if(autoCloseWhenFinished){ this.executeFunctionWhenChange(playList, 0, endFunction); } var mediaClass = media.get('class'); var isVideo = mediaClass == 'Video' || mediaClass == 'Video360'; playList.set('selectedIndex', 0); if(isVideo){ this.bind('resize', resizeFunction, this); resizeFunction(); playList.get('items')[0].get('player').play(); } else { w.set('width', popupMaxWidth); w.set('height', popupMaxHeight); } this.MainViewer.set('toolTipEnabled', false); if(stopAudios) { this.pauseGlobalAudios(); } var playersPaused = this.pauseCurrentPlayers(!stopAudios); w.bind('close', closeFunction, this); w.show(this, true); },
  "pauseGlobalAudiosWhilePlayItem": function(playList, index, exclude){  var self = this; var item = playList.get('items')[index]; var media = item.get('media'); var player = item.get('player'); var caller = media.get('id'); var endFunc = function(){ if(playList.get('selectedIndex') != index) { if(hasState){ player.unbind('stateChange', stateChangeFunc, self); } self.resumeGlobalAudios(caller); } }; var stateChangeFunc = function(event){ var state = event.data.state; if(state == 'stopped'){ this.resumeGlobalAudios(caller); } else if(state == 'playing'){ this.pauseGlobalAudios(caller, exclude); } }; var mediaClass = media.get('class'); var hasState = mediaClass == 'Video360' || mediaClass == 'Video'; if(hasState){ player.bind('stateChange', stateChangeFunc, this); } this.pauseGlobalAudios(caller, exclude); this.executeFunctionWhenChange(playList, index, endFunc, endFunc); },
  "loopAlbum": function(playList, index){  var playListItem = playList.get('items')[index]; var player = playListItem.get('player'); var loopFunction = function(){ player.play(); }; this.executeFunctionWhenChange(playList, index, loopFunction); },
  "stopGlobalAudio": function(audio){  var audios = window.currentGlobalAudios; if(audios){ audio = audios[audio.get('id')]; if(audio){ delete audios[audio.get('id')]; if(Object.keys(audios).length == 0){ window.currentGlobalAudios = undefined; } } } if(audio) audio.stop(); },
  "shareFacebook": function(url){  window.open('https://www.facebook.com/sharer/sharer.php?u=' + url, '_blank'); },
  "historyGoForward": function(playList){  var history = this.get('data')['history'][playList.get('id')]; if(history != undefined) { history.forward(); } },
  "getCurrentPlayerWithMedia": function(media){  var playerClass = undefined; var mediaPropertyName = undefined; switch(media.get('class')) { case 'Panorama': case 'LivePanorama': case 'HDRPanorama': playerClass = 'PanoramaPlayer'; mediaPropertyName = 'panorama'; break; case 'Video360': playerClass = 'PanoramaPlayer'; mediaPropertyName = 'video'; break; case 'PhotoAlbum': playerClass = 'PhotoAlbumPlayer'; mediaPropertyName = 'photoAlbum'; break; case 'Map': playerClass = 'MapPlayer'; mediaPropertyName = 'map'; break; case 'Video': playerClass = 'VideoPlayer'; mediaPropertyName = 'video'; break; }; if(playerClass != undefined) { var players = this.getByClassName(playerClass); for(var i = 0; i<players.length; ++i){ var player = players[i]; if(player.get(mediaPropertyName) == media) { return player; } } } else { return undefined; } },
  "isCardboardViewMode": function(){  var players = this.getByClassName('PanoramaPlayer'); return players.length > 0 && players[0].get('viewMode') == 'cardboard'; },
  "initGA": function(){  var sendFunc = function(category, event, label) { ga('send', 'event', category, event, label); }; var media = this.getByClassName('Panorama'); media = media.concat(this.getByClassName('Video360')); media = media.concat(this.getByClassName('Map')); for(var i = 0, countI = media.length; i<countI; ++i){ var m = media[i]; var mediaLabel = m.get('label'); var overlays = this.getOverlays(m); for(var j = 0, countJ = overlays.length; j<countJ; ++j){ var overlay = overlays[j]; var overlayLabel = overlay.get('data') != undefined ? mediaLabel + ' - ' + overlay.get('data')['label'] : mediaLabel; switch(overlay.get('class')) { case 'HotspotPanoramaOverlay': case 'HotspotMapOverlay': var areas = overlay.get('areas'); for (var z = 0; z<areas.length; ++z) { areas[z].bind('click', sendFunc.bind(this, 'Hotspot', 'click', overlayLabel), this); } break; case 'CeilingCapPanoramaOverlay': case 'TripodCapPanoramaOverlay': overlay.bind('click', sendFunc.bind(this, 'Cap', 'click', overlayLabel), this); break; } } } var components = this.getByClassName('Button'); components = components.concat(this.getByClassName('IconButton')); for(var i = 0, countI = components.length; i<countI; ++i){ var c = components[i]; var componentLabel = c.get('data')['name']; c.bind('click', sendFunc.bind(this, 'Skin', 'click', componentLabel), this); } var items = this.getByClassName('PlayListItem'); var media2Item = {}; for(var i = 0, countI = items.length; i<countI; ++i) { var item = items[i]; var media = item.get('media'); if(!(media.get('id') in media2Item)) { item.bind('begin', sendFunc.bind(this, 'Media', 'play', media.get('label')), this); media2Item[media.get('id')] = item; } } },
  "setMapLocation": function(panoramaPlayListItem, mapPlayer){  var resetFunction = function(){ panoramaPlayListItem.unbind('stop', resetFunction, this); player.set('mapPlayer', null); }; panoramaPlayListItem.bind('stop', resetFunction, this); var player = panoramaPlayListItem.get('player'); player.set('mapPlayer', mapPlayer); },
  "loadFromCurrentMediaPlayList": function(playList, delta){  var currentIndex = playList.get('selectedIndex'); var totalItems = playList.get('items').length; var newIndex = (currentIndex + delta) % totalItems; while(newIndex < 0){ newIndex = totalItems + newIndex; }; if(currentIndex != newIndex){ playList.set('selectedIndex', newIndex); } },
  "fixTogglePlayPauseButton": function(player){  var state = player.get('state'); var buttons = player.get('buttonPlayPause'); if(typeof buttons !== 'undefined' && player.get('state') == 'playing'){ if(!Array.isArray(buttons)) buttons = [buttons]; for(var i = 0; i<buttons.length; ++i) buttons[i].set('pressed', true); } },
  "historyGoBack": function(playList){  var history = this.get('data')['history'][playList.get('id')]; if(history != undefined) { history.back(); } },
  "setMediaBehaviour": function(playList, index, mediaDispatcher){  var self = this; var stateChangeFunction = function(event){ if(event.data.state == 'stopped'){ dispose.call(this, true); } }; var onBeginFunction = function() { item.unbind('begin', onBeginFunction, self); var media = item.get('media'); if(media.get('class') != 'Panorama' || (media.get('camera') != undefined && media.get('camera').get('initialSequence') != undefined)){ player.bind('stateChange', stateChangeFunction, self); } }; var changeFunction = function(){ var index = playListDispatcher.get('selectedIndex'); if(index != -1){ indexDispatcher = index; dispose.call(this, false); } }; var disposeCallback = function(){ dispose.call(this, false); }; var dispose = function(forceDispose){ if(!playListDispatcher) return; var media = item.get('media'); if((media.get('class') == 'Video360' || media.get('class') == 'Video') && media.get('loop') == true && !forceDispose) return; playList.set('selectedIndex', -1); if(panoramaSequence && panoramaSequenceIndex != -1){ if(panoramaSequence) { if(panoramaSequenceIndex > 0 && panoramaSequence.get('movements')[panoramaSequenceIndex-1].get('class') == 'TargetPanoramaCameraMovement'){ var initialPosition = camera.get('initialPosition'); var oldYaw = initialPosition.get('yaw'); var oldPitch = initialPosition.get('pitch'); var oldHfov = initialPosition.get('hfov'); var previousMovement = panoramaSequence.get('movements')[panoramaSequenceIndex-1]; initialPosition.set('yaw', previousMovement.get('targetYaw')); initialPosition.set('pitch', previousMovement.get('targetPitch')); initialPosition.set('hfov', previousMovement.get('targetHfov')); var restoreInitialPositionFunction = function(event){ initialPosition.set('yaw', oldYaw); initialPosition.set('pitch', oldPitch); initialPosition.set('hfov', oldHfov); itemDispatcher.unbind('end', restoreInitialPositionFunction, this); }; itemDispatcher.bind('end', restoreInitialPositionFunction, this); } panoramaSequence.set('movementIndex', panoramaSequenceIndex); } } if(player){ item.unbind('begin', onBeginFunction, this); player.unbind('stateChange', stateChangeFunction, this); for(var i = 0; i<buttons.length; ++i) { buttons[i].unbind('click', disposeCallback, this); } } if(sameViewerArea){ var currentMedia = this.getMediaFromPlayer(player); if(currentMedia == undefined || currentMedia == item.get('media')){ playListDispatcher.set('selectedIndex', indexDispatcher); } if(playList != playListDispatcher) playListDispatcher.unbind('change', changeFunction, this); } else{ viewerArea.set('visible', viewerVisibility); } playListDispatcher = undefined; }; var mediaDispatcherByParam = mediaDispatcher != undefined; if(!mediaDispatcher){ var currentIndex = playList.get('selectedIndex'); var currentPlayer = (currentIndex != -1) ? playList.get('items')[playList.get('selectedIndex')].get('player') : this.getActivePlayerWithViewer(this.MainViewer); if(currentPlayer) { mediaDispatcher = this.getMediaFromPlayer(currentPlayer); } } var playListDispatcher = mediaDispatcher ? this.getPlayListWithMedia(mediaDispatcher, true) : undefined; if(!playListDispatcher){ playList.set('selectedIndex', index); return; } var indexDispatcher = playListDispatcher.get('selectedIndex'); if(playList.get('selectedIndex') == index || indexDispatcher == -1){ return; } var item = playList.get('items')[index]; var itemDispatcher = playListDispatcher.get('items')[indexDispatcher]; var player = item.get('player'); var viewerArea = player.get('viewerArea'); var viewerVisibility = viewerArea.get('visible'); var sameViewerArea = viewerArea == itemDispatcher.get('player').get('viewerArea'); if(sameViewerArea){ if(playList != playListDispatcher){ playListDispatcher.set('selectedIndex', -1); playListDispatcher.bind('change', changeFunction, this); } } else{ viewerArea.set('visible', true); } var panoramaSequenceIndex = -1; var panoramaSequence = undefined; var camera = itemDispatcher.get('camera'); if(camera){ panoramaSequence = camera.get('initialSequence'); if(panoramaSequence) { panoramaSequenceIndex = panoramaSequence.get('movementIndex'); } } playList.set('selectedIndex', index); var buttons = []; var addButtons = function(property){ var value = player.get(property); if(value == undefined) return; if(Array.isArray(value)) buttons = buttons.concat(value); else buttons.push(value); }; addButtons('buttonStop'); for(var i = 0; i<buttons.length; ++i) { buttons[i].bind('click', disposeCallback, this); } if(player != itemDispatcher.get('player') || !mediaDispatcherByParam){ item.bind('begin', onBeginFunction, self); } this.executeFunctionWhenChange(playList, index, disposeCallback); },
  "getPlayListItemByMedia": function(playList, media){  var items = playList.get('items'); for(var j = 0, countJ = items.length; j<countJ; ++j){ var item = items[j]; if(item.get('media') == media) return item; } return undefined; },
  "syncPlaylists": function(playLists){  var changeToMedia = function(media, playListDispatched){ for(var i = 0, count = playLists.length; i<count; ++i){ var playList = playLists[i]; if(playList != playListDispatched){ var items = playList.get('items'); for(var j = 0, countJ = items.length; j<countJ; ++j){ if(items[j].get('media') == media){ if(playList.get('selectedIndex') != j){ playList.set('selectedIndex', j); } break; } } } } }; var changeFunction = function(event){ var playListDispatched = event.source; var selectedIndex = playListDispatched.get('selectedIndex'); if(selectedIndex < 0) return; var media = playListDispatched.get('items')[selectedIndex].get('media'); changeToMedia(media, playListDispatched); }; var mapPlayerChangeFunction = function(event){ var panoramaMapLocation = event.source.get('panoramaMapLocation'); if(panoramaMapLocation){ var map = panoramaMapLocation.get('map'); changeToMedia(map); } }; for(var i = 0, count = playLists.length; i<count; ++i){ playLists[i].bind('change', changeFunction, this); } var mapPlayers = this.getByClassName('MapPlayer'); for(var i = 0, count = mapPlayers.length; i<count; ++i){ mapPlayers[i].bind('panoramaMapLocation_change', mapPlayerChangeFunction, this); } },
  "getKey": function(key){  return window[key]; },
  "setEndToItemIndex": function(playList, fromIndex, toIndex){  var endFunction = function(){ if(playList.get('selectedIndex') == fromIndex) playList.set('selectedIndex', toIndex); }; this.executeFunctionWhenChange(playList, fromIndex, endFunction); },
  "triggerOverlay": function(overlay, eventName){  if(overlay.get('areas') != undefined) { var areas = overlay.get('areas'); for(var i = 0; i<areas.length; ++i) { areas[i].trigger(eventName); } } else { overlay.trigger(eventName); } },
  "getActivePlayerWithViewer": function(viewerArea){  var players = this.getByClassName('PanoramaPlayer'); players = players.concat(this.getByClassName('VideoPlayer')); players = players.concat(this.getByClassName('Video360Player')); players = players.concat(this.getByClassName('PhotoAlbumPlayer')); players = players.concat(this.getByClassName('MapPlayer')); var i = players.length; while(i-- > 0){ var player = players[i]; if(player.get('viewerArea') == viewerArea) { var playerClass = player.get('class'); if(playerClass == 'PanoramaPlayer' && (player.get('panorama') != undefined || player.get('video') != undefined)) return player; else if((playerClass == 'VideoPlayer' || playerClass == 'Video360Player') && player.get('video') != undefined) return player; else if(playerClass == 'PhotoAlbumPlayer' && player.get('photoAlbum') != undefined) return player; else if(playerClass == 'MapPlayer' && player.get('map') != undefined) return player; } } return undefined; },
  "getMediaFromPlayer": function(player){  switch(player.get('class')){ case 'PanoramaPlayer': return player.get('panorama') || player.get('video'); case 'VideoPlayer': case 'Video360Player': return player.get('video'); case 'PhotoAlbumPlayer': return player.get('photoAlbum'); case 'MapPlayer': return player.get('map'); } },
  "changeBackgroundWhilePlay": function(playList, index, color){  var stopFunction = function(event){ playListItem.unbind('stop', stopFunction, this); if((color == viewerArea.get('backgroundColor')) && (colorRatios == viewerArea.get('backgroundColorRatios'))){ viewerArea.set('backgroundColor', backgroundColorBackup); viewerArea.set('backgroundColorRatios', backgroundColorRatiosBackup); } }; var playListItem = playList.get('items')[index]; var player = playListItem.get('player'); var viewerArea = player.get('viewerArea'); var backgroundColorBackup = viewerArea.get('backgroundColor'); var backgroundColorRatiosBackup = viewerArea.get('backgroundColorRatios'); var colorRatios = [0]; if((color != backgroundColorBackup) || (colorRatios != backgroundColorRatiosBackup)){ viewerArea.set('backgroundColor', color); viewerArea.set('backgroundColorRatios', colorRatios); playListItem.bind('stop', stopFunction, this); } },
  "pauseGlobalAudio": function(audio){  var audios = window.currentGlobalAudios; if(audios){ audio = audios[audio.get('id')]; } if(audio.get('state') == 'playing') audio.pause(); },
  "setComponentVisibility": function(component, visible, applyAt, effect, propertyEffect, ignoreClearTimeout){  var keepVisibility = this.getKey('keepVisibility_' + component.get('id')); if(keepVisibility) return; this.unregisterKey('visibility_'+component.get('id')); var changeVisibility = function(){ if(effect && propertyEffect){ component.set(propertyEffect, effect); } component.set('visible', visible); if(component.get('class') == 'ViewerArea'){ try{ if(visible) component.restart(); else if(component.get('playbackState') == 'playing') component.pause(); } catch(e){}; } }; var effectTimeoutName = 'effectTimeout_'+component.get('id'); if(!ignoreClearTimeout && window.hasOwnProperty(effectTimeoutName)){ var effectTimeout = window[effectTimeoutName]; if(effectTimeout instanceof Array){ for(var i=0; i<effectTimeout.length; i++){ clearTimeout(effectTimeout[i]) } }else{ clearTimeout(effectTimeout); } delete window[effectTimeoutName]; } else if(visible == component.get('visible') && !ignoreClearTimeout) return; if(applyAt && applyAt > 0){ var effectTimeout = setTimeout(function(){ if(window[effectTimeoutName] instanceof Array) { var arrayTimeoutVal = window[effectTimeoutName]; var index = arrayTimeoutVal.indexOf(effectTimeout); arrayTimeoutVal.splice(index, 1); if(arrayTimeoutVal.length == 0){ delete window[effectTimeoutName]; } }else{ delete window[effectTimeoutName]; } changeVisibility(); }, applyAt); if(window.hasOwnProperty(effectTimeoutName)){ window[effectTimeoutName] = [window[effectTimeoutName], effectTimeout]; }else{ window[effectTimeoutName] = effectTimeout; } } else{ changeVisibility(); } },
  "getPlayListWithMedia": function(media, onlySelected){  var playLists = this.getByClassName('PlayList'); for(var i = 0, count = playLists.length; i<count; ++i){ var playList = playLists[i]; if(onlySelected && playList.get('selectedIndex') == -1) continue; if(this.getPlayListItemByMedia(playList, media) != undefined) return playList; } return undefined; },
  "keepComponentVisibility": function(component, keep){  var key = 'keepVisibility_' + component.get('id'); var value = this.getKey(key); if(value == undefined && keep) { this.registerKey(key, keep); } else if(value != undefined && !keep) { this.unregisterKey(key); } },
  "getPlayListItems": function(media, player){  var itemClass = (function() { switch(media.get('class')) { case 'Panorama': case 'LivePanorama': case 'HDRPanorama': return 'PanoramaPlayListItem'; case 'Video360': return 'Video360PlayListItem'; case 'PhotoAlbum': return 'PhotoAlbumPlayListItem'; case 'Map': return 'MapPlayListItem'; case 'Video': return 'VideoPlayListItem'; } })(); if (itemClass != undefined) { var items = this.getByClassName(itemClass); for (var i = items.length-1; i>=0; --i) { var item = items[i]; if(item.get('media') != media || (player != undefined && item.get('player') != player)) { items.splice(i, 1); } } return items; } else { return []; } },
  "init": function(){  if(!Object.hasOwnProperty('values')) { Object.values = function(o){ return Object.keys(o).map(function(e) { return o[e]; }); }; } var history = this.get('data')['history']; var playListChangeFunc = function(e){ var playList = e.source; var index = playList.get('selectedIndex'); if(index < 0) return; var id = playList.get('id'); if(!history.hasOwnProperty(id)) history[id] = new HistoryData(playList); history[id].add(index); }; var playLists = this.getByClassName('PlayList'); for(var i = 0, count = playLists.length; i<count; ++i) { var playList = playLists[i]; playList.bind('change', playListChangeFunc, this); } },
  "startPanoramaWithCamera": function(media, camera){  if(window.currentPanoramasWithCameraChanged != undefined && window.currentPanoramasWithCameraChanged.indexOf(media) != -1){ return; } var playLists = this.getByClassName('PlayList'); if(playLists.length == 0) return; var restoreItems = []; for(var i = 0, count = playLists.length; i<count; ++i){ var playList = playLists[i]; var items = playList.get('items'); for(var j = 0, countJ = items.length; j<countJ; ++j){ var item = items[j]; if(item.get('media') == media && (item.get('class') == 'PanoramaPlayListItem' || item.get('class') == 'Video360PlayListItem')){ restoreItems.push({camera: item.get('camera'), item: item}); item.set('camera', camera); } } } if(restoreItems.length > 0) { if(window.currentPanoramasWithCameraChanged == undefined) { window.currentPanoramasWithCameraChanged = [media]; } else { window.currentPanoramasWithCameraChanged.push(media); } var restoreCameraOnStop = function(){ var index = window.currentPanoramasWithCameraChanged.indexOf(media); if(index != -1) { window.currentPanoramasWithCameraChanged.splice(index, 1); } for (var i = 0; i < restoreItems.length; i++) { restoreItems[i].item.set('camera', restoreItems[i].camera); restoreItems[i].item.unbind('stop', restoreCameraOnStop, this); } }; for (var i = 0; i < restoreItems.length; i++) { restoreItems[i].item.bind('stop', restoreCameraOnStop, this); } } },
  "showPopupPanoramaOverlay": function(popupPanoramaOverlay, closeButtonProperties, imageHD, toggleImage, toggleImageHD, autoCloseMilliSeconds, audio, stopBackgroundAudio){  var self = this; this.MainViewer.set('toolTipEnabled', false); var cardboardEnabled = this.isCardboardViewMode(); if(!cardboardEnabled) { var zoomImage = this.zoomImagePopupPanorama; var showDuration = popupPanoramaOverlay.get('showDuration'); var hideDuration = popupPanoramaOverlay.get('hideDuration'); var playersPaused = this.pauseCurrentPlayers(audio == null || !stopBackgroundAudio); var popupMaxWidthBackup = popupPanoramaOverlay.get('popupMaxWidth'); var popupMaxHeightBackup = popupPanoramaOverlay.get('popupMaxHeight'); var showEndFunction = function() { var loadedFunction = function(){ if(!self.isCardboardViewMode()) popupPanoramaOverlay.set('visible', false); }; popupPanoramaOverlay.unbind('showEnd', showEndFunction, self); popupPanoramaOverlay.set('showDuration', 1); popupPanoramaOverlay.set('hideDuration', 1); self.showPopupImage(imageHD, toggleImageHD, popupPanoramaOverlay.get('popupMaxWidth'), popupPanoramaOverlay.get('popupMaxHeight'), null, null, closeButtonProperties, autoCloseMilliSeconds, audio, stopBackgroundAudio, loadedFunction, hideFunction); }; var hideFunction = function() { var restoreShowDurationFunction = function(){ popupPanoramaOverlay.unbind('showEnd', restoreShowDurationFunction, self); popupPanoramaOverlay.set('visible', false); popupPanoramaOverlay.set('showDuration', showDuration); popupPanoramaOverlay.set('popupMaxWidth', popupMaxWidthBackup); popupPanoramaOverlay.set('popupMaxHeight', popupMaxHeightBackup); }; self.resumePlayers(playersPaused, audio == null || !stopBackgroundAudio); var currentWidth = zoomImage.get('imageWidth'); var currentHeight = zoomImage.get('imageHeight'); popupPanoramaOverlay.bind('showEnd', restoreShowDurationFunction, self, true); popupPanoramaOverlay.set('showDuration', 1); popupPanoramaOverlay.set('hideDuration', hideDuration); popupPanoramaOverlay.set('popupMaxWidth', currentWidth); popupPanoramaOverlay.set('popupMaxHeight', currentHeight); if(popupPanoramaOverlay.get('visible')) restoreShowDurationFunction(); else popupPanoramaOverlay.set('visible', true); self.MainViewer.set('toolTipEnabled', true); }; if(!imageHD){ imageHD = popupPanoramaOverlay.get('image'); } if(!toggleImageHD && toggleImage){ toggleImageHD = toggleImage; } popupPanoramaOverlay.bind('showEnd', showEndFunction, this, true); } else { var hideEndFunction = function() { self.resumePlayers(playersPaused, audio == null || stopBackgroundAudio); if(audio){ if(stopBackgroundAudio){ self.resumeGlobalAudios(); } self.stopGlobalAudio(audio); } popupPanoramaOverlay.unbind('hideEnd', hideEndFunction, self); self.MainViewer.set('toolTipEnabled', true); }; var playersPaused = this.pauseCurrentPlayers(audio == null || !stopBackgroundAudio); if(audio){ if(stopBackgroundAudio){ this.pauseGlobalAudios(); } this.playGlobalAudio(audio); } popupPanoramaOverlay.bind('hideEnd', hideEndFunction, this, true); } popupPanoramaOverlay.set('visible', true); },
  "getPixels": function(value){  var result = new RegExp('((\\+|\\-)?\\d+(\\.\\d*)?)(px|vw|vh|vmin|vmax)?', 'i').exec(value); if (result == undefined) { return 0; } var num = parseFloat(result[1]); var unit = result[4]; var vw = this.rootPlayer.get('actualWidth') / 100; var vh = this.rootPlayer.get('actualHeight') / 100; switch(unit) { case 'vw': return num * vw; case 'vh': return num * vh; case 'vmin': return num * Math.min(vw, vh); case 'vmax': return num * Math.max(vw, vh); default: return num; } },
  "setPanoramaCameraWithCurrentSpot": function(playListItem){  var currentPlayer = this.getActivePlayerWithViewer(this.MainViewer); if(currentPlayer == undefined){ return; } var playerClass = currentPlayer.get('class'); if(playerClass != 'PanoramaPlayer' && playerClass != 'Video360Player'){ return; } var fromMedia = currentPlayer.get('panorama'); if(fromMedia == undefined) { fromMedia = currentPlayer.get('video'); } var panorama = playListItem.get('media'); var newCamera = this.cloneCamera(playListItem.get('camera')); this.setCameraSameSpotAsMedia(newCamera, fromMedia); this.startPanoramaWithCamera(panorama, newCamera); },
  "stopAndGoCamera": function(camera, ms){  var sequence = camera.get('initialSequence'); sequence.pause(); var timeoutFunction = function(){ sequence.play(); }; setTimeout(timeoutFunction, ms); },
  "changePlayListWithSameSpot": function(playList, newIndex){  var currentIndex = playList.get('selectedIndex'); if (currentIndex >= 0 && newIndex >= 0 && currentIndex != newIndex) { var currentItem = playList.get('items')[currentIndex]; var newItem = playList.get('items')[newIndex]; var currentPlayer = currentItem.get('player'); var newPlayer = newItem.get('player'); if ((currentPlayer.get('class') == 'PanoramaPlayer' || currentPlayer.get('class') == 'Video360Player') && (newPlayer.get('class') == 'PanoramaPlayer' || newPlayer.get('class') == 'Video360Player')) { var newCamera = this.cloneCamera(newItem.get('camera')); this.setCameraSameSpotAsMedia(newCamera, currentItem.get('media')); this.startPanoramaWithCamera(newItem.get('media'), newCamera); } } },
  "getGlobalAudio": function(audio){  var audios = window.currentGlobalAudios; if(audios != undefined && audio.get('id') in audios){ audio = audios[audio.get('id')]; } return audio; },
  "autotriggerAtStart": function(playList, callback, once){  var onChange = function(event){ callback(); if(once == true) playList.unbind('change', onChange, this); }; playList.bind('change', onChange, this); },
  "visibleComponentsIfPlayerFlagEnabled": function(components, playerFlag){  var enabled = this.get(playerFlag); for(var i in components){ components[i].set('visible', enabled); } },
  "setPanoramaCameraWithSpot": function(playListItem, yaw, pitch){  var panorama = playListItem.get('media'); var newCamera = this.cloneCamera(playListItem.get('camera')); var initialPosition = newCamera.get('initialPosition'); initialPosition.set('yaw', yaw); initialPosition.set('pitch', pitch); this.startPanoramaWithCamera(panorama, newCamera); },
  "pauseCurrentPlayers": function(onlyPauseCameraIfPanorama){  var players = this.getCurrentPlayers(); var i = players.length; while(i-- > 0){ var player = players[i]; if(player.get('state') == 'playing') { if(onlyPauseCameraIfPanorama && player.get('class') == 'PanoramaPlayer' && typeof player.get('video') === 'undefined'){ player.pauseCamera(); } else { player.pause(); } } else { players.splice(i, 1); } } return players; },
  "getComponentByName": function(name){  var list = this.getByClassName('UIComponent'); for(var i = 0, count = list.length; i<count; ++i){ var component = list[i]; var data = component.get('data'); if(data != undefined && data.name == name){ return component; } } return undefined; },
  "updateVideoCues": function(playList, index){  var playListItem = playList.get('items')[index]; var video = playListItem.get('media'); if(video.get('cues').length == 0) return; var player = playListItem.get('player'); var cues = []; var changeFunction = function(){ if(playList.get('selectedIndex') != index){ video.unbind('cueChange', cueChangeFunction, this); playList.unbind('change', changeFunction, this); } }; var cueChangeFunction = function(event){ var activeCues = event.data.activeCues; for(var i = 0, count = cues.length; i<count; ++i){ var cue = cues[i]; if(activeCues.indexOf(cue) == -1 && (cue.get('startTime') > player.get('currentTime') || cue.get('endTime') < player.get('currentTime')+0.5)){ cue.trigger('end'); } } cues = activeCues; }; video.bind('cueChange', cueChangeFunction, this); playList.bind('change', changeFunction, this); },
  "updateMediaLabelFromPlayList": function(playList, htmlText, playListItemStopToDispose){  var changeFunction = function(){ var index = playList.get('selectedIndex'); if(index >= 0){ var beginFunction = function(){ playListItem.unbind('begin', beginFunction); setMediaLabel(index); }; var setMediaLabel = function(index){ var media = playListItem.get('media'); var text = media.get('data'); if(!text) text = media.get('label'); setHtml(text); }; var setHtml = function(text){ if(text !== undefined) { htmlText.set('html', '<div style=\"text-align:left\"><SPAN STYLE=\"color:#FFFFFF;font-size:12px;font-family:Verdana\"><span color=\"white\" font-family=\"Verdana\" font-size=\"12px\">' + text + '</SPAN></div>'); } else { htmlText.set('html', ''); } }; var playListItem = playList.get('items')[index]; if(htmlText.get('html')){ setHtml('Loading...'); playListItem.bind('begin', beginFunction); } else{ setMediaLabel(index); } } }; var disposeFunction = function(){ htmlText.set('html', undefined); playList.unbind('change', changeFunction, this); playListItemStopToDispose.unbind('stop', disposeFunction, this); }; if(playListItemStopToDispose){ playListItemStopToDispose.bind('stop', disposeFunction, this); } playList.bind('change', changeFunction, this); changeFunction(); },
  "showPopupImage": function(image, toggleImage, customWidth, customHeight, showEffect, hideEffect, closeButtonProperties, autoCloseMilliSeconds, audio, stopBackgroundAudio, loadedCallback, hideCallback){  var self = this; var closed = false; var playerClickFunction = function() { zoomImage.unbind('loaded', loadedFunction, self); hideFunction(); }; var clearAutoClose = function(){ zoomImage.unbind('click', clearAutoClose, this); if(timeoutID != undefined){ clearTimeout(timeoutID); } }; var resizeFunction = function(){ setTimeout(setCloseButtonPosition, 0); }; var loadedFunction = function(){ self.unbind('click', playerClickFunction, self); veil.set('visible', true); setCloseButtonPosition(); closeButton.set('visible', true); zoomImage.unbind('loaded', loadedFunction, this); zoomImage.bind('userInteractionStart', userInteractionStartFunction, this); zoomImage.bind('userInteractionEnd', userInteractionEndFunction, this); zoomImage.bind('resize', resizeFunction, this); timeoutID = setTimeout(timeoutFunction, 200); }; var timeoutFunction = function(){ timeoutID = undefined; if(autoCloseMilliSeconds){ var autoCloseFunction = function(){ hideFunction(); }; zoomImage.bind('click', clearAutoClose, this); timeoutID = setTimeout(autoCloseFunction, autoCloseMilliSeconds); } zoomImage.bind('backgroundClick', hideFunction, this); if(toggleImage) { zoomImage.bind('click', toggleFunction, this); zoomImage.set('imageCursor', 'hand'); } closeButton.bind('click', hideFunction, this); if(loadedCallback) loadedCallback(); }; var hideFunction = function() { self.MainViewer.set('toolTipEnabled', true); closed = true; if(timeoutID) clearTimeout(timeoutID); if (timeoutUserInteractionID) clearTimeout(timeoutUserInteractionID); if(autoCloseMilliSeconds) clearAutoClose(); if(hideCallback) hideCallback(); zoomImage.set('visible', false); if(hideEffect && hideEffect.get('duration') > 0){ hideEffect.bind('end', endEffectFunction, this); } else{ zoomImage.set('image', null); } closeButton.set('visible', false); veil.set('visible', false); self.unbind('click', playerClickFunction, self); zoomImage.unbind('backgroundClick', hideFunction, this); zoomImage.unbind('userInteractionStart', userInteractionStartFunction, this); zoomImage.unbind('userInteractionEnd', userInteractionEndFunction, this, true); zoomImage.unbind('resize', resizeFunction, this); if(toggleImage) { zoomImage.unbind('click', toggleFunction, this); zoomImage.set('cursor', 'default'); } closeButton.unbind('click', hideFunction, this); self.resumePlayers(playersPaused, audio == null || stopBackgroundAudio); if(audio){ if(stopBackgroundAudio){ self.resumeGlobalAudios(); } self.stopGlobalAudio(audio); } }; var endEffectFunction = function() { zoomImage.set('image', null); hideEffect.unbind('end', endEffectFunction, this); }; var toggleFunction = function() { zoomImage.set('image', isToggleVisible() ? image : toggleImage); }; var isToggleVisible = function() { return zoomImage.get('image') == toggleImage; }; var setCloseButtonPosition = function() { var right = zoomImage.get('actualWidth') - zoomImage.get('imageLeft') - zoomImage.get('imageWidth') + 10; var top = zoomImage.get('imageTop') + 10; if(right < 10) right = 10; if(top < 10) top = 10; closeButton.set('right', right); closeButton.set('top', top); }; var userInteractionStartFunction = function() { if(timeoutUserInteractionID){ clearTimeout(timeoutUserInteractionID); timeoutUserInteractionID = undefined; } else{ closeButton.set('visible', false); } }; var userInteractionEndFunction = function() { if(!closed){ timeoutUserInteractionID = setTimeout(userInteractionTimeoutFunction, 300); } }; var userInteractionTimeoutFunction = function() { timeoutUserInteractionID = undefined; closeButton.set('visible', true); setCloseButtonPosition(); }; this.MainViewer.set('toolTipEnabled', false); var veil = this.veilPopupPanorama; var zoomImage = this.zoomImagePopupPanorama; var closeButton = this.closeButtonPopupPanorama; if(closeButtonProperties){ for(var key in closeButtonProperties){ closeButton.set(key, closeButtonProperties[key]); } } var playersPaused = this.pauseCurrentPlayers(audio == null || !stopBackgroundAudio); if(audio){ if(stopBackgroundAudio){ this.pauseGlobalAudios(); } this.playGlobalAudio(audio); } var timeoutID = undefined; var timeoutUserInteractionID = undefined; zoomImage.bind('loaded', loadedFunction, this); setTimeout(function(){ self.bind('click', playerClickFunction, self, false); }, 0); zoomImage.set('image', image); zoomImage.set('customWidth', customWidth); zoomImage.set('customHeight', customHeight); zoomImage.set('showEffect', showEffect); zoomImage.set('hideEffect', hideEffect); zoomImage.set('visible', true); return zoomImage; },
  "playAudioList": function(audios){  if(audios.length == 0) return; var currentAudioCount = -1; var currentAudio; var playGlobalAudioFunction = this.playGlobalAudio; var playNext = function(){ if(++currentAudioCount >= audios.length) currentAudioCount = 0; currentAudio = audios[currentAudioCount]; playGlobalAudioFunction(currentAudio, playNext); }; playNext(); },
  "setOverlayBehaviour": function(overlay, media, action){  var executeFunc = function() { switch(action){ case 'triggerClick': this.triggerOverlay(overlay, 'click'); break; case 'stop': case 'play': case 'pause': overlay[action](); break; case 'togglePlayPause': case 'togglePlayStop': if(overlay.get('state') == 'playing') overlay[action == 'togglePlayPause' ? 'pause' : 'stop'](); else overlay.play(); break; } if(window.overlaysDispatched == undefined) window.overlaysDispatched = {}; var id = overlay.get('id'); window.overlaysDispatched[id] = true; setTimeout(function(){ delete window.overlaysDispatched[id]; }, 2000); }; if(window.overlaysDispatched != undefined && overlay.get('id') in window.overlaysDispatched) return; var playList = this.getPlayListWithMedia(media, true); if(playList != undefined){ var item = this.getPlayListItemByMedia(playList, media); if(playList.get('items').indexOf(item) != playList.get('selectedIndex')){ var beginFunc = function(e){ item.unbind('begin', beginFunc, this); executeFunc.call(this); }; item.bind('begin', beginFunc, this); return; } } executeFunc.call(this); },
  "setStartTimeVideoSync": function(video, player){  this.setStartTimeVideo(video, player.get('currentTime')); },
  "registerKey": function(key, value){  window[key] = value; },
  "showWindow": function(w, autoCloseMilliSeconds, containsAudio){  if(w.get('visible') == true){ return; } var closeFunction = function(){ clearAutoClose(); this.resumePlayers(playersPaused, !containsAudio); w.unbind('close', closeFunction, this); }; var clearAutoClose = function(){ w.unbind('click', clearAutoClose, this); if(timeoutID != undefined){ clearTimeout(timeoutID); } }; var timeoutID = undefined; if(autoCloseMilliSeconds){ var autoCloseFunction = function(){ w.hide(); }; w.bind('click', clearAutoClose, this); timeoutID = setTimeout(autoCloseFunction, autoCloseMilliSeconds); } var playersPaused = this.pauseCurrentPlayers(!containsAudio); w.bind('close', closeFunction, this); w.show(this, true); },
  "playGlobalAudioWhilePlay": function(playList, index, audio, endCallback){  var changeFunction = function(event){ if(event.data.previousSelectedIndex == index){ this.stopGlobalAudio(audio); if(isPanorama) { var media = playListItem.get('media'); var audios = media.get('audios'); audios.splice(audios.indexOf(audio), 1); media.set('audios', audios); } playList.unbind('change', changeFunction, this); if(endCallback) endCallback(); } }; var audios = window.currentGlobalAudios; if(audios && audio.get('id') in audios){ audio = audios[audio.get('id')]; if(audio.get('state') != 'playing'){ audio.play(); } return audio; } playList.bind('change', changeFunction, this); var playListItem = playList.get('items')[index]; var isPanorama = playListItem.get('class') == 'PanoramaPlayListItem'; if(isPanorama) { var media = playListItem.get('media'); var audios = (media.get('audios') || []).slice(); if(audio.get('class') == 'MediaAudio') { var panoramaAudio = this.rootPlayer.createInstance('PanoramaAudio'); panoramaAudio.set('autoplay', false); panoramaAudio.set('audio', audio.get('audio')); panoramaAudio.set('loop', audio.get('loop')); panoramaAudio.set('id', audio.get('id')); var stateChangeFunctions = audio.getBindings('stateChange'); for(var i = 0; i<stateChangeFunctions.length; ++i){ var f = stateChangeFunctions[i]; if(typeof f == 'string') f = new Function('event', f); panoramaAudio.bind('stateChange', f, this); } audio = panoramaAudio; } audios.push(audio); media.set('audios', audios); } return this.playGlobalAudio(audio, endCallback); },
  "showPopupPanoramaVideoOverlay": function(popupPanoramaOverlay, closeButtonProperties, stopAudios){  var self = this; var showEndFunction = function() { popupPanoramaOverlay.unbind('showEnd', showEndFunction); closeButton.bind('click', hideFunction, this); setCloseButtonPosition(); closeButton.set('visible', true); }; var endFunction = function() { if(!popupPanoramaOverlay.get('loop')) hideFunction(); }; var hideFunction = function() { self.MainViewer.set('toolTipEnabled', true); popupPanoramaOverlay.set('visible', false); closeButton.set('visible', false); closeButton.unbind('click', hideFunction, self); popupPanoramaOverlay.unbind('end', endFunction, self); popupPanoramaOverlay.unbind('hideEnd', hideFunction, self, true); self.resumePlayers(playersPaused, true); if(stopAudios) { self.resumeGlobalAudios(); } }; var setCloseButtonPosition = function() { var right = 10; var top = 10; closeButton.set('right', right); closeButton.set('top', top); }; this.MainViewer.set('toolTipEnabled', false); var closeButton = this.closeButtonPopupPanorama; if(closeButtonProperties){ for(var key in closeButtonProperties){ closeButton.set(key, closeButtonProperties[key]); } } var playersPaused = this.pauseCurrentPlayers(true); if(stopAudios) { this.pauseGlobalAudios(); } popupPanoramaOverlay.bind('end', endFunction, this, true); popupPanoramaOverlay.bind('showEnd', showEndFunction, this, true); popupPanoramaOverlay.bind('hideEnd', hideFunction, this, true); popupPanoramaOverlay.set('visible', true); },
  "setStartTimeVideo": function(video, time){  var items = this.getPlayListItems(video); var startTimeBackup = []; var restoreStartTimeFunc = function() { for(var i = 0; i<items.length; ++i){ var item = items[i]; item.set('startTime', startTimeBackup[i]); item.unbind('stop', restoreStartTimeFunc, this); } }; for(var i = 0; i<items.length; ++i) { var item = items[i]; var player = item.get('player'); if(player.get('video') == video && player.get('state') == 'playing') { player.seek(time); } else { startTimeBackup.push(item.get('startTime')); item.set('startTime', time); item.bind('stop', restoreStartTimeFunc, this); } } },
  "pauseGlobalAudios": function(caller, exclude){  if (window.pauseGlobalAudiosState == undefined) window.pauseGlobalAudiosState = {}; if (window.pauseGlobalAudiosList == undefined) window.pauseGlobalAudiosList = []; if (caller in window.pauseGlobalAudiosState) { return; } var audios = this.getByClassName('Audio').concat(this.getByClassName('VideoPanoramaOverlay')); if (window.currentGlobalAudios != undefined) audios = audios.concat(Object.values(window.currentGlobalAudios)); var audiosPaused = []; var values = Object.values(window.pauseGlobalAudiosState); for (var i = 0, count = values.length; i<count; ++i) { var objAudios = values[i]; for (var j = 0; j<objAudios.length; ++j) { var a = objAudios[j]; if(audiosPaused.indexOf(a) == -1) audiosPaused.push(a); } } window.pauseGlobalAudiosState[caller] = audiosPaused; for (var i = 0, count = audios.length; i < count; ++i) { var a = audios[i]; if (a.get('state') == 'playing' && (exclude == undefined || exclude.indexOf(a) == -1)) { a.pause(); audiosPaused.push(a); } } },
  "getPanoramaOverlayByName": function(panorama, name){  var overlays = this.getOverlays(panorama); for(var i = 0, count = overlays.length; i<count; ++i){ var overlay = overlays[i]; var data = overlay.get('data'); if(data != undefined && data.label == name){ return overlay; } } return undefined; },
  "setMainMediaByIndex": function(index){  var item = undefined; if(index >= 0 && index < this.mainPlayList.get('items').length){ this.mainPlayList.set('selectedIndex', index); item = this.mainPlayList.get('items')[index]; } return item; },
  "cloneCamera": function(camera){  var newCamera = this.rootPlayer.createInstance(camera.get('class')); newCamera.set('id', camera.get('id') + '_copy'); newCamera.set('idleSequence', camera.get('initialSequence')); return newCamera; },
  "playGlobalAudio": function(audio, endCallback){  var endFunction = function(){ audio.unbind('end', endFunction, this); this.stopGlobalAudio(audio); if(endCallback) endCallback(); }; audio = this.getGlobalAudio(audio); var audios = window.currentGlobalAudios; if(!audios){ audios = window.currentGlobalAudios = {}; } audios[audio.get('id')] = audio; if(audio.get('state') == 'playing'){ return audio; } if(!audio.get('loop')){ audio.bind('end', endFunction, this); } audio.play(); return audio; },
  "getMediaWidth": function(media){  switch(media.get('class')){ case 'Video360': var res = media.get('video'); if(res instanceof Array){ var maxW=0; for(var i=0; i<res.length; i++){ var r = res[i]; if(r.get('width') > maxW) maxW = r.get('width'); } return maxW; }else{ return r.get('width') } default: return media.get('width'); } },
  "shareWhatsapp": function(url){  window.open('https://api.whatsapp.com/send/?text=' + encodeURIComponent(url), '_blank'); },
  "shareTwitter": function(url){  window.open('https://twitter.com/intent/tweet?source=webclient&url=' + url, '_blank'); },
  "unregisterKey": function(key){  delete window[key]; },
  "getMediaHeight": function(media){  switch(media.get('class')){ case 'Video360': var res = media.get('video'); if(res instanceof Array){ var maxH=0; for(var i=0; i<res.length; i++){ var r = res[i]; if(r.get('height') > maxH) maxH = r.get('height'); } return maxH; }else{ return r.get('height') } default: return media.get('height'); } },
  "resumePlayers": function(players, onlyResumeCameraIfPanorama){  for(var i = 0; i<players.length; ++i){ var player = players[i]; if(onlyResumeCameraIfPanorama && player.get('class') == 'PanoramaPlayer' && typeof player.get('video') === 'undefined'){ player.resumeCamera(); } else{ player.play(); } } },
  "existsKey": function(key){  return key in window; },
  "resumeGlobalAudios": function(caller){  if (window.pauseGlobalAudiosState == undefined || !(caller in window.pauseGlobalAudiosState)) return; var audiosPaused = window.pauseGlobalAudiosState[caller]; delete window.pauseGlobalAudiosState[caller]; var values = Object.values(window.pauseGlobalAudiosState); for (var i = 0, count = values.length; i<count; ++i) { var objAudios = values[i]; for (var j = audiosPaused.length-1; j>=0; --j) { var a = audiosPaused[j]; if(objAudios.indexOf(a) != -1) audiosPaused.splice(j, 1); } } for (var i = 0, count = audiosPaused.length; i<count; ++i) { var a = audiosPaused[i]; if (a.get('state') == 'paused') a.play(); } },
  "getMediaByName": function(name){  var list = this.getByClassName('Media'); for(var i = 0, count = list.length; i<count; ++i){ var media = list[i]; if((media.get('class') == 'Audio' && media.get('data').label == name) || media.get('label') == name){ return media; } } return undefined; },
  "openLink": function(url, name){  if(url == location.href) { return; } var isElectron = (window && window.process && window.process.versions && window.process.versions['electron']) || (navigator && navigator.userAgent && navigator.userAgent.indexOf('Electron') >= 0); if (name == '_blank' && isElectron) { if (url.startsWith('/')) { var r = window.location.href.split('/'); r.pop(); url = r.join('/') + url; } var extension = url.split('.').pop().toLowerCase(); if(extension != 'pdf' || url.startsWith('file://')) { var shell = window.require('electron').shell; shell.openExternal(url); } else { window.open(url, name); } } else if(isElectron && (name == '_top' || name == '_self')) { window.location = url; } else { var newWindow = window.open(url, name); newWindow.focus(); } },
  "executeFunctionWhenChange": function(playList, index, endFunction, changeFunction){  var endObject = undefined; var changePlayListFunction = function(event){ if(event.data.previousSelectedIndex == index){ if(changeFunction) changeFunction.call(this); if(endFunction && endObject) endObject.unbind('end', endFunction, this); playList.unbind('change', changePlayListFunction, this); } }; if(endFunction){ var playListItem = playList.get('items')[index]; if(playListItem.get('class') == 'PanoramaPlayListItem'){ var camera = playListItem.get('camera'); if(camera != undefined) endObject = camera.get('initialSequence'); if(endObject == undefined) endObject = camera.get('idleSequence'); } else{ endObject = playListItem.get('media'); } if(endObject){ endObject.bind('end', endFunction, this); } } playList.bind('change', changePlayListFunction, this); }
 },
 "scrollBarColor": "#000000",
 "data": {
  "name": "Player480"
 },
 "mouseWheelEnabled": true,
 "shadow": false,
 "downloadEnabled": false,
 "horizontalAlign": "left",
 "gap": 10
};

    
    function HistoryData(playList) {
        this.playList = playList;
        this.list = [];
        this.pointer = -1;
    }

    HistoryData.prototype.add = function(index){
        if(this.pointer < this.list.length && this.list[this.pointer] == index) {
            return;
        }
        ++this.pointer;
        this.list.splice(this.pointer, this.list.length - this.pointer, index);
    };

    HistoryData.prototype.back = function(){
        if(!this.canBack()) return;
        this.playList.set('selectedIndex', this.list[--this.pointer]);
    };

    HistoryData.prototype.forward = function(){
        if(!this.canForward()) return;
        this.playList.set('selectedIndex', this.list[++this.pointer]);
    };

    HistoryData.prototype.canBack = function(){
        return this.pointer > 0;
    };

    HistoryData.prototype.canForward = function(){
        return this.pointer >= 0 && this.pointer < this.list.length-1;
    };
    //

    if(script.data == undefined)
        script.data = {};
    script.data["history"] = {};    //playListID -> HistoryData

    TDV.PlayerAPI.defineScript(script);
})();
