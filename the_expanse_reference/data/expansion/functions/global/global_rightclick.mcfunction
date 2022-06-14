# place vehicles
execute if predicate expansion:nbt_checks/selected_item/vehicles/moon_buggy run function expansion:vehicles/buggy/place
execute if predicate expansion:nbt_checks/selected_item/vehicles/spaceship run function expansion:vehicles/spaceship/place
execute if predicate expansion:nbt_checks/selected_item/vehicles/rocket run function expansion:vehicles/rocket/place
execute if predicate expansion:nbt_checks/selected_item/vehicles/lunar_module run function expansion:vehicles/lunar_module/place
execute if predicate expansion:nbt_checks/selected_item/vehicles/return_capsule run function expansion:vehicles/return_capsule/place

# terraformer
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/terraformer run function expansion:blocks/terraformer/place
# crafter
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/crafter run function expansion:blocks/crafter/place
# lacrymae extractor
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/lacrymae_extractor run function expansion:blocks/lacrymae_extractor/place
# planetarium
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/planetarium if entity @e[tag=planetarium,limit=1,sort=nearest,distance=..22] run function expansion:handy_tools/error_messages/planetarium_error
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/planetarium unless entity @e[tag=planetarium,limit=1,sort=nearest,distance=..22] run function expansion:blocks/planetarium/place
# planetarium used for markers in space
execute if predicate expansion:nbt_checks/selected_item/blocks/planetarium if predicate expansion:dimension/space if entity @s[tag=inside_spaceship] run function expansion:vehicles/spaceship/markers/marker_switcher
# compressor
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/compressor run function expansion:blocks/compressor/place
# enhancer
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/enhancer run function expansion:blocks/enhancer/place
# arc_furnace
execute if block ~ ~1.75 ~ #expansion:expansion_airliq if predicate expansion:nbt_checks/selected_item/blocks/arc_furnace run function expansion:blocks/arc_furnace/place

# cryoblaster
execute if entity @s[predicate=expansion:nbt_checks/selected_item/items/cryoblaster,tag=!inside_spaceship] run function expansion:items/cryoblaster/cryoblaster
# wrench
execute if entity @s[predicate=expansion:nbt_checks/selected_item/items/wrench,predicate=expansion:utility/sneak,tag=!inside_spaceship] rotated ~ 0 positioned ^ ^ ^2.5 as @e[type=armor_stand,distance=..2.5,limit=1,sort=nearest] at @s run function expansion:items/wrench
# space equipment
execute if entity @s[predicate=expansion:nbt_checks/selected_item/space_equipment/equipment,tag=!inside_spaceship] run function expansion:items/space_equipment/equip
# spaceship blasters
execute if entity @s[predicate=expansion:nbt_checks/selected_item/items/blasters,tag=inside_spaceship,scores={fuel_level=1..}] unless predicate expansion:nbt_checks/selected_item/blocks/planetarium unless predicate expansion:nbt_checks/selected_item/items/fleet_finder run function expansion:vehicles/spaceship/blasters/fire
# oxygen system
execute if entity @s[nbt={Inventory:[{Slot:103b,id:"minecraft:carrot_on_a_stick",tag:{space_jetpack:1b}}],SelectedItem:{tag:{oxygen_tank:1b}}}] run function expansion:global/oxygen_regulation/refill_check
# pocket space transporter
execute if entity @s[predicate=expansion:nbt_checks/selected_item/items/pocket_space,tag=!inside_vehicle,tag=!landing_moon,tag=!landing_earth] unless score @s exp_cooldown matches 1.. unless score @s exp_warmup matches 1.. run function expansion:items/transporter/switcher
# fleet finder
execute if predicate expansion:nbt_checks/selected_item/items/fleet_finder if predicate expansion:dimension/jupiter run function expansion:items/fleet_finder
# railgun
execute if predicate expansion:nbt_checks/selected_item/items/railgun unless score @s exp_warmup matches 1.. unless score @s exp_cooldown matches 1.. run function expansion:items/railgun/main
# reset scoreboard
scoreboard players set @s exp_rightclick 0
scoreboard players reset @s[scores={exp_warmup=0}] exp_warmup

