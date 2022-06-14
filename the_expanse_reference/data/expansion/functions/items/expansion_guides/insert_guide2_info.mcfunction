data remove entity @s Item.tag.pages

#frontpage
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_frontpage_title","color":"black","bold":"true"},{"translate":"exp_guide2_frontpage_body","color":"black"},{"translate":"exp_guide2_frontpage_signature","color":"black"}]'
# contents
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_contents_title","color":"black","bold":"true"},{"translate":"exp_guide2_contents_spaceship","color":"black","clickEvent":{"action":"change_page","value":"3"}},{"translate":"exp_guide2_contents_lacrymae","color":"black","clickEvent":{"action":"change_page","value":"4"}},{"translate":"exp_guide2_contents_tospace","color":"black","clickEvent":{"action":"change_page","value":"5"}},{"translate":"exp_guide2_contents_planetarium","color":"black","clickEvent":{"action":"change_page","value":"6"}},{"translate":"exp_guide2_contents_modules","color":"black","clickEvent":{"action":"change_page","value":"7"}},{"translate":"exp_guide2_contents_toplanet","color":"black","clickEvent":{"action":"change_page","value":"9"}}]'
#spaceship
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_spaceship_title","color":"black","bold":"true"},{"translate":"exp_guide2_spaceship_body","color":"black"}]'
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_spaceship_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff20"}]},{"translate":"exp_guide2_spaceship2_body",color":"black"}]'
#lacrymae intro
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_lacrymae_title","color":"black","bold":"true"},{"translate":"exp_guide2_lacrymae_body","color":"black"}]'
#going to space
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_tospace_title","color":"black","bold":"true"},{"translate":"exp_guide2_tospace_body","color":"black"}]'
#planetarium
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_planetarium_title","color":"black","bold":"true"},{"translate":"exp_guide2_planetarium_body","color":"black"}]'
# modules
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_modules_title",color":"black","bold":"true"},{"translate":"exp_guide2_modules_body",color":"black"}]'
# enhancer
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_enhancer_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff14"}]},{"translate":"exp_guide2_enhancer_body",color":"black"}]'
#going to planets
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_toplanet_title","color":"black","bold":"true"},{"translate":"exp_guide2_toplanet_body","color":"black"}]'
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide2_toplanet_title","color":"black","bold":"true"},{"translate":"exp_guide2_toplanet_body2","color":"black"}]'

#data modify entity @s Item.tag.pages append value '[{"translate":"exp_guide_middle_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff01"}]}]'
#data modify entity @s Item.tag.pages append value '[{"text":"HELLO!"}]'
