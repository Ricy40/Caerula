data remove entity @s Item.tag.pages

# frontpage
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"Download the Resourcepack!",color":"black","bold":"true"},{"text":"\\n\\n"},{"translate":"So you are telling me that you missed the massive required resourcepack button beneath the download button?",color":"black"},{"text":"\\n"},{"translate":"And you ALSO overlooked the enormous red instruction on the PMC page? Bruh moment...",color":"black"}]'
# contents
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_contents_title",color":"black","bold":"true"},{"translate":"exp_guide1_contents_start",color":"black","clickEvent":{"action":"change_page","value":"3"}},{"translate":"exp_guide1_contents_recipes",color":"black","clickEvent":{"action":"change_page","value":"6"}},{"translate":"exp_guide1_contents_oxygen",color":"black","clickEvent":{"action":"change_page","value":"10"}},{"translate":"exp_guide1_contents_arc_furnace",color":"black","clickEvent":{"action":"change_page","value":"15"}},{"translate":"exp_guide1_contents_rocket",color":"black","clickEvent":{"action":"change_page","value":"16"}},{"translate":"exp_guide1_contents_buggy",color":"black","clickEvent":{"action":"change_page","value":"24"}}]'
# getting started
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_start_title",color":"black","bold":"true"},{"translate":"exp_guide1_start_body",color":"black"}]'
# steel
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_steel_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff00"}]},{"translate":"exp_guide1_steel_body",color":"black"}]'
# workbench
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_bench_title",color":"black","bold":"true"},{"translate":"exp_guide1_bench_body",color":"black"}]'

# items page
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_items_title",color":"black","bold":"true"},{"translate":"exp_guide1_items_body",color":"black"}]'
# steel plate
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_plate_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff01"}]}]'
# processing unit
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_cpu_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff06"}]}]'
# wrench
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_wrench_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff02"}]},{"translate":"exp_guide1_wrench_body",color":"black"}]'

# oxygen and modules
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_oxygen_title",color":"black","bold":"true"},{"translate":"exp_guide1_oxygen_body",color":"black"}]'
# space equipment
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_equipment_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff04"}]},{"translate":"exp_guide1_equipment_body",color":"black"}]'
# oxygen tank
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_tank_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff03"}]},{"translate":"exp_guide1_tank_body",color":"black"}]'
# compressor
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_compressor_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff07"}]},{"translate":"exp_guide1_compressor_body",color":"black"}]'
# terraformer
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_terraformer_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff23"}]},{"translate":"exp_guide1_terraformer_body",color":"black"}]'
# compressor
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_arc_furnace_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff25"}]},{"translate":"exp_guide1_arc_furnace_body",color":"black"}]'

# rocket
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rocket_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff11"}]},{"translate":"exp_guide1_rocket_body",color":"black"}]'
# rocket top
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rockettop_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff08"}]}]'
# rocket cockpit
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rocketcockpit_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff09"}]}]'
# rocket engines
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rocketengines_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff10"}]}]'
# rocket explain
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rocket_title",color":"black","bold":"true"},{"translate":"exp_guide1_rocket_body2",color":"black"}]'
# rocket explain2
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rocketinstr_title",color":"black","bold":"true"},{"translate":"exp_guide1_rocket_body3",color":"black"}]'
# rocket explain3
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rocketinstr_title",color":"black","bold":"true"},{"translate":"exp_guide1_rocket_body4",color":"black"}]'
# rocket explain4
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_rocketinstr_title",color":"black","bold":"true"},{"translate":"exp_guide1_rocket_body5",color":"black"}]'

# moon buggy
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_buggy_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff13"}]},{"translate":"exp_guide1_buggy_body",color":"black"}]'
# buggy wheel
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_buggywheel_title",color":"black","bold":"true"},{"translate":"exp_guide_top_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff12"}]}]'
# buggy explain
data modify entity @s Item.tag.pages append value '[{"text":"","font":"minecraft:default","bold":false,"color":"black"},{"translate":"exp_guide1_buggyinstr_title",color":"black","bold":"true"},{"translate":"exp_guide1_buggy_body1",color":"black"}]'

#data modify entity @s Item.tag.pages append value '[{"translate":"exp_guide1_middle_center_image","color":"white","font":"expansion:default","with":[{"text":"\\uff01"}]}]'
#data modify entity @s Item.tag.pages append value '[{"text":"HELLO!"}]'
